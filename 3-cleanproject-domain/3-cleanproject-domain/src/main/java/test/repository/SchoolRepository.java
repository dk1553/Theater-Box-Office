package test.repository;


import test.model.School;
import test.model.Student;
import test.model.Teacher;
import test.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SchoolRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public SchoolRepository() {
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public SchoolRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    public School add(School school) {
        entityManager.getTransaction().begin();
        entityManager.persist(school);
        entityManager.getTransaction().commit();
        return school;
    }

    public School find(Long id) {
        return entityManager.find(School.class, id);
    }

    public School update(School school) {
        School schoolToUpdate = find(school.getId());
        entityManager.getTransaction().begin();
        schoolToUpdate.setCity(school.getCity());
        schoolToUpdate.setName(school.getName());
        entityManager.getTransaction().commit();
        return schoolToUpdate;
    }

    public void delete(School school) {
        entityManager.getTransaction().begin();
        entityManager.remove(school);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }

    public static class App {
      public static void main(String[] args) {

        Student student = new Student("Alan", "Red");

        //REPOSITORIES

        StudentRepository studentRepository = new StudentRepository();

        SchoolRepository schoolRepository = new SchoolRepository();

        TutorRepository tutorRepository = new TutorRepository();

        TeacherRepository teacherRepository = new TeacherRepository();

        //ADD STUDENT

        studentRepository.add(student);

        System.out.println("Added student " + student.toString());

        //ADD TUTOR

        Tutor tutor = new Tutor("FirstName_tutor_1", "LastName_tutor_2");

        tutorRepository.add(tutor);

        System.out.println("Added tutor " + tutor.toString());

        studentRepository.addTutor(student.getId(), tutor);

        System.out.println("Student with tutor " + student.toString());

        System.out.println("Found student with school " + student.toString());

        //ADD SCHOOL

        School school = new School("School_1","City_1");

        schoolRepository.add(school);

        System.out.println("Added school " + school.toString());

        school = schoolRepository.find(school.getId());

        school.getStudents().forEach(System.out::println);

        school = schoolRepository.find(school.getId());

        school.getStudents().forEach(System.out::println);

        //ADD TEACHER

        Teacher teacher = new Teacher("firstname_1","lastname_1");

        teacher.addStudent(new Student("SFirstName_1", "SLastname_1"));
        teacher.addStudent(new Student("SFirstName_2", "SLastname_2"));

        teacherRepository.add(teacher);

        //Persistence Operations and JPQL

        studentRepository.findFirstNames().forEach(System.out::println);

        studentRepository.findLastNames().forEach(System.out::println);

        student = studentRepository.find(student.getId());

        System.out.println("Found student " + student.toString());

        student = studentRepository.findById(student.getId());

        System.out.println("Found student (JPQL) " + student.toString());

        student.setLastName("Green");

        studentRepository.update(student);

        System.out.println("Updated student " + student.toString());

        student = studentRepository.updateFirstNameById("Fred", student.getId());

        System.out.println("Updated first name (JPQL)" + student.toString());

        student = studentRepository.updateLastNameById("Yellow", student.getId());

        System.out.println("Updated last name (JPQL)" + student.toString());

        List<Student> students = studentRepository.findByFirstNameStartWith("Fr");

        students.forEach(System.out::println);

        students = studentRepository.findByLastNameEndWith("ow");

        students.forEach(System.out::println);

        System.out.println("Number of student(s): "+  studentRepository.count());

        students = studentRepository.findSortingByFirstName();

        students.forEach(System.out::println);

        students = studentRepository.findSortingById();

        students.forEach(System.out::println);

        //repository.delete(student);

        //System.out.println("Deleted student " + student.toString());


        //CRITERIA BUILDER

        List<Student> studentList = studentRepository.getStudentWithCriteriaBuilder();

        System.out.println("Print Students (Criteria Builder): ");
        studentList.forEach(System.out::println);

        List<Student> studentListWhere = studentRepository.getStudentsWithWHEREFirstName();

        System.out.println("Print Students (Criteria Builder with WHERE and GROUP BY): ");
        studentListWhere.forEach(System.out::println);

        studentRepository.close();

      }
    }
}

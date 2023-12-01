package com.geektrust.backend.repositoryservice;

import dto.CourseDto;
import entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ICourseRepository;
import repositoryservice.impl.CourseRepositoryServiceImpl;
import util.Messages;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class CourseRepositoryServiceTest {

    @Mock
    private ICourseRepository repositoryMock;

    @InjectMocks
    private CourseRepositoryServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        CourseDto courseDTO = CourseDto.builder()
                .courseID("OFFERING-DATASCIENCE-BOB")
                .courseName("DATASCIENCE")
                .instructor("BOB")
                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
                .minimumEmployee(1)
                .maximumEmployee(3).build();
        CourseDto courseDTO2 = CourseDto.builder()
                .courseID("OFFERING-PYTHON-JOHN")
                .courseName("PYTHON")
                .instructor("JOHN")
                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
                .minimumEmployee(1)
                .maximumEmployee(3).build();
        courseService.save(courseDTO);
        courseService.save(courseDTO2);
        List<CourseDto> all = courseService.findAll();
        assertEquals(2, all.size());


    }

//    @Test
//    void findByID() {
//        // Given
//        String courseId = "OFFERING-DATASCIENCE-BOB";
//
//        Course course = Course.builder()
//                .courseID(courseId)
//                .courseName("DATASCIENCE")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1)
//                .maximumEmployee(3).build();
//
//        courseRepository.save(course);
//
//        Optional<Course> byId = courseRepository.findById(courseId);
//
//        assertTrue(byId.isPresent());
//        assertEquals(courseId, byId.get().getCourseID());
//        assertEquals(course, byId.get());
//
//
//    }
//
//    @Test
//    void checkAllotedCourses() throws Exception {
//        Course course = Course.builder()
//                .courseID("OFFERING-DATASCIENCE-BOB")
//                .courseName("DATASCIENCE")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(true)
//                .maximumEmployee(3).build();
//        Course course2 = Course.builder()
//                .courseID("OFFERING-PYTHON-JOHN")
//                .courseName("PYTHON")
//                .instructor("JOHN")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        courseRepository.save(course);
//        courseRepository.save(course2);
//        List<Course> allotedCourse = courseRepository.findAllotedCourse();
//        assertEquals(1, allotedCourse.size());
//    }
//
//    @Test
//    void findAll() throws Exception {
//        Course course = Course.builder()
//                .courseID("OFFERING-DATASCIENCE-BOB")
//                .courseName("DATASCIENCE")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(true)
//                .maximumEmployee(3).build();
//        Course course2 = Course.builder()
//                .courseID("OFFERING-PYTHON-JOHN")
//                .courseName("PYTHON")
//                .instructor("JOHN")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        courseRepository.save(course);
//        courseRepository.save(course2);
//        List<Course> allotedCourse = courseRepository.findAll();
//        assertEquals(2, allotedCourse.size());
//    }
//
//    @Test
//    void checkNullCourseID() {
//
//        String courseId = null;
//
//        Optional<Course> byId = courseRepository.findById(courseId);
//        assertFalse(byId.isPresent());
//    }
//
//    @Test
//    void testExistByIDWithNull()
//    {
//        String courseId =null;
//        boolean b = courseRepository.existById(courseId);
//        assertFalse(b);
//    }
//
//    @Test
//    void testAllotedCoursesWithNoCourses()
//    {
//        Course course = Course.builder()
//                .courseID("OFFERING-DATASCIENCE-BOB")
//                .courseName("DATASCIENCE")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        Course course2 = Course.builder()
//                .courseID("OFFERING-PYTHON-JOHN")
//                .courseName("PYTHON")
//                .instructor("JOHN")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        courseRepository.save(course);
//        courseRepository.save(course2);
//        List<Course> allotedCourse = courseRepository.findAllotedCourse();
//        assertEquals(0, allotedCourse.size());
//    }
//
//    @Test
//    void saveDuplicateCourse()
//    {
//        Course course = Course.builder()
//                .courseID("OFFERING-DATASCIENCE-BOB")
//                .courseName("JAVA")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        Course course2 = Course.builder()
//                .courseID("OFFERING-DATASCIENCE-BOB")
//                .courseName("DATASCIENCE")
//                .instructor("BOB")
//                .date(LocalDate.parse("05062022", Messages.DATE_FORMATTER_PATTERN))
//                .minimumEmployee(1).isAlloted(false)
//                .maximumEmployee(3).build();
//        courseRepository.save(course);
//        courseRepository.save(course2);
//        Optional<Course> byId = courseRepository.findById("OFFERING-DATASCIENCE-BOB");
//        assertTrue(byId.isPresent());
//        assertSame("DATASCIENCE", byId.get().getCourseName());
//    }
}

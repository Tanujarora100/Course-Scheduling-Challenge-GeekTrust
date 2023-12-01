package repositoryservice.impl;

import dto.CourseDto;
import entity.Course;
import repository.ICourseRepository;
import repositoryservice.ICourseRepositoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepositoryServiceImpl implements ICourseRepositoryService {
    private final ICourseRepository courseRepository;


    public CourseRepositoryServiceImpl(ICourseRepository repository) {
        this.courseRepository = repository;
    }

    @Override
    public CourseDto save(CourseDto course) {
        Course courseObject = mapToCourse(course);
        String courseID = "OFFERING" + courseObject.getCourseName() + "-" + courseObject.getInstructor();
        courseObject.setCourseID(courseID);
        courseRepository.save(courseObject);
        Optional<Course> byId = courseRepository.findById(courseID);
        return mapToDTO(byId.get());
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> all = courseRepository.findAll();
        return all.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> findById(String courseID) {
        Course course = courseRepository.findById(courseID).orElse(null);
        return (course != null) ? Optional.ofNullable(mapToDTO(course)) : Optional.empty();
    }


    @Override
    public void delete(CourseDto object) {
        String courseID = object.getCourseID();
        boolean ans = validateID(courseID);
        if (ans) {
            courseRepository.deleteByID(courseID);
        }
    }

    @Override
    public void deleteByID(String courseID) {
        if (validateID(courseID))
            courseRepository.deleteByID(courseID);
    }

    @Override
    public boolean validateID(String courseID) {
        return courseID != null && courseRepository.existById(courseID);
    }


    @Override
    public boolean existByID(String courseID) {
        return validateID(courseID);
    }


    @Override
    public List<CourseDto> findAllotedCourses() {
        List<Course> allottedCourses = courseRepository.findAllotedCourse();
        return allottedCourses.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Course mapToCourse(CourseDto courseDto) {
        return Course.builder()
                .courseName(courseDto.getCourseName())
                .courseID(courseDto.getCourseID())
                .instructor(courseDto.getInstructor())
                .employeeMap(courseDto.getEmployeeMap())
                .minimumEmployee(courseDto.getMinimumEmployee())
                .maximumEmployee(courseDto.getMaximumEmployee())
                .date(courseDto.getDate())
                .isCancelled(courseDto.isCancelled())
                .isAlloted(courseDto.isAlloted())
                .build();
    }


    public CourseDto mapToDTO(Course course) {
        return CourseDto.builder()
                .courseName(course.getCourseName())
                .courseID(course.getCourseID())
                .date(course.getDate())
                .isAlloted(course.isAlloted())
                .maximumEmployee(course.getMaximumEmployee())
                .isCancelled(course.isCancelled())
                .instructor(course.getInstructor())
                .minimumEmployee(course.getMinimumEmployee())
                .maximumEmployee(course.getMaximumEmployee())
                .build();

    }


}

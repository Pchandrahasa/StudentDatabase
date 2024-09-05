CREATE TABLE IF NOT EXISTS course (
    courseId INT PRIMARY KEY AUTO_INCREMENT,
    courseName VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);


CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    rollno VARCHAR(20),
    courseId INT,
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);

CREATE TABLE IF NOT EXISTS student_course (
    studentId INT,
    courseId INT,
    PRIMARY KEY (studentId, courseID),
    FOREIGN KEY (studentId) REFERENCES student(id),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);

CREATE TABLE IF NOT EXISTS teacher (
    teacherId INT PRIMARY KEY AUTO_INCREMENT,
    teacherName VARCHAR(100) NOT NULL,
    department VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS course_teacher (
    courseID INT,
    teacherId INT,
    PRIMARY KEY (courseID, teacherID),
    FOREIGN KEY (courseID) REFERENCES course(courseID),
    FOREIGN KEY (teacherID) REFERENCES teacher(teacherId)
);



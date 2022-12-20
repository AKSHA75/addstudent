@PostMapping("/add-student")
public String addStudent(@ModelAttribute Student student) {
   // Save the student to the database
   studentRepository.save(student);
   return "redirect:/students";
}
 @PostMapping("/add-marks")
public String addMarks(@ModelAttribute Marks marks) {
   // Save the marks to the database
   marksRepository.save(marks);
   return "redirect:/marks";
}
 @GetMapping("/average-percentage")
public String getAveragePercentage(Model model) {
   // Retrieve the most recent semester
   Semester semester = semesterRepository.findTopByOrderByYearDesc();

   // Calculate the total marks scored by all students in all subjects in the most recent semester
   int totalMarks = marksRepository.sumMarksBySemester(semester);

   // Calculate the total number of students and subjects
   int numStudents = studentRepository.count();
   int numSubjects = subjectRepository.count();

   // Calculate the average percentage
   double averagePercentage = (totalMarks / (numStudents * numSubjects)) * 100;

   // Add the average percentage to the model
   model.addAttribute("averagePercentage", averagePercentage);

   // Render the average percentage report
   return "average-percentage";
}
<head>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<form class="form-horizontal">
   <div class="form-group">
      <label for="inputName" class="col-sm-2 control-label">Name</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="inputName" placeholder="Name">
      </div>
   </div>
   <div class="form-group">
      <label for="inputRollNumber" class="col-sm-2 control-label">Roll Number</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="inputRollNumber" placeholder="Roll Number">
      </div>
   </div>
   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
         <button type="submit" class="btn btn-primary">Add Student</button>
      </div>
   </div>
</form>
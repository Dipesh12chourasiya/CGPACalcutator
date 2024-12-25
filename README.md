# CGPA Calculator 📚

A simple and user-friendly Android application to calculate and track CGPA (Cumulative Grade Point Average) using Jetpack Compose and Material3.

---

## Features ✨

- **Input Grades and Credits:**
  - Easily input grades (A, B, C, D, etc.) and corresponding credit values for subjects.
  
- **Calculate CGPA:**
  - Calculates CGPA based on the entered grades and credits.
  
- **Track Previous Semesters:**
  - Keeps a record of grades and credits for previous semesters.
  
- **Interactive User Interface:**
  - Built with Jetpack Compose for a responsive, modern UI.
  - Uses Material3 design for enhanced user experience.

---

## Screenshots 📸

Coming Soon!

---

## Tech Stack 🛠️

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Design System:** Material3

---

Features
Input Grades and Credits:

Users can input grades (A, B, C, D, etc.) and corresponding credits for up to four subjects.
CGPA Calculation:

Calculates the CGPA based on grades and credits entered by the user.
Displays the overall CGPA after calculation.
Track Previous Semesters:

Stores data of previous semesters, including grades and credits.
Displays a summary of previous semester performance.
Interactive UI:

Built with Jetpack Compose for a modern and responsive user interface.
Includes Material3 design components for an enhanced user experience.
Key Components
Data Class:

Semester: Represents a semester with grade and credit.
MainActivity:

Entry point of the application.
Initializes and sets up the Compose UI with CGPA composable.
Composable Functions:

CGPA: The main composable that handles the grade and credit input, calculates CGPA, and displays the results.
subjectText: Displays the subject title.
GradeTextField: Input field for grades.
CreditTextField: Input field for credits.
Spacer8dp: Adds spacing between UI elements.
Utility Function:

calculateGradePoint: Converts grades to grade points and calculates the total grade points for credits.
User Interface
Header:

Displays "CGPA Calculator" at the top with serif font styling.
Subject Input Fields:

Each subject has:
A grade input field.
A credit input field.
Calculate Button:

When clicked:
Adds the entered grades and credits to the semester list.
Computes the CGPA.
Results Display:

Shows the current CGPA in a styled card.
Displays previous semesters’ grades and credits.
Code Highlights
Dynamic State Management:

Uses remember and mutableStateOf to manage user inputs and CGPA dynamically.
Theming:

Applies Material3 theming with consistent colors, shapes, and typography.
Reusable Composables:

Components like GradeTextField and CreditTextField are designed to be reusable and customizable.
Technical Details
Dependencies:

Jetpack Compose: For declarative UI.
Material3: For modern UI components.
Gradle Setup:

Add the following dependencies:
groovy
Copy code
implementation "androidx.compose.ui:ui:1.6.0"
implementation "androidx.compose.material3:material3:1.1.0"
debugImplementation "androidx.compose.ui:ui-tooling:1.6.0"
Error Handling:

Graceful handling of invalid inputs (e.g., empty fields or non-numeric credit values).

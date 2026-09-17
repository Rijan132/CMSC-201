/*
 * Class: CMSC203 
 * Instructor: Professor Ahmed Tarek
 * Description: (This program should read students grade from files and calculate average and total grade and report it to a file.)
 * Due: 09/17/2026
 * Platform/compiler:
 * I pledge that I have completed the programming assignment 
  independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Rijan Bishwakarma
*/

package gradecalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {

        String courseName = "CMSC203 Computer Science I";
        int weightOfProject = 40;
        int weightOfQuiz = 30;
        int weightOfExam = 30;
        boolean defaultUsed = false;

        System.out.println("========================================");
        System.out.println("   CMSC203 Project 1 - Grade Calculator");
        System.out.println("========================================");
        System.out.println("\nLoading configuration from gradeconfig.txt ...");

        try {
            Scanner config = new Scanner(new File("gradeconfig.txt"));

            courseName = config.nextLine();
            int numberOfCategories = config.nextInt();
            int totalWeight = 0;
            for (int i = 0; i < numberOfCategories; i++) {
                String category = config.next();
                int weight = config.nextInt();

                totalWeight = totalWeight + weight;

                if (category.equals("Projects")) {
                    weightOfProject = weight;
                } else if (category.equals("Quizzes")) {
                    weightOfQuiz = weight;
                } else if (category.equals("Exams")) {
                    weightOfExam = weight;
                }
            }
            config.close();
            if (totalWeight == 100) {
                System.out.println("Configuration loaded successfully.");
            } else {
                System.out.println("Invalid configuration.");
                System.out.println("Using default configuration.");
                weightOfProject = 40;
                weightOfQuiz = 30;
                weightOfExam = 30;
                defaultUsed = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found.");
            System.out.println("Using default configuration.");
            defaultUsed = true;
        }
        System.out.println("\nUsing input file: grades_input.txt");
        System.out.println("Using output file: grades_report.txt\n");
       
        System.out.println("Reading student scores...");

        try {
            Scanner input = new Scanner(new File("grades_input.txt"));
            String firstName = input.next();
            String lastName = input.next();
            String category = input.next();
            int numberOfScores = input.nextInt();
            double total = 0;
            int validScores = 0;

            if (category.equals("Projects") && numberOfScores > 0) {
                for (int i = 0; i < numberOfScores; i++) {
                    double score = input.nextDouble();

                    if (score >= 0 && score <= 100) {
                        total = total + score;
                        validScores++;
                    } else {
                        System.out.println("Invalid Project score: " + score);
                    }
                }
            } else {
                System.out.println("Error: Invalid Projects data.");
            }
            double projectAverage = 0;

            if (validScores > 0) {
                projectAverage = total / validScores;
            }
            category = input.next();
            numberOfScores = input.nextInt();
            total = 0;
            validScores = 0;
            if (category.equals("Quizzes") && numberOfScores > 0) {
                for (int i = 0; i < numberOfScores; i++) {
                    double score = input.nextDouble();

                    if (score >= 0 && score <= 100) {
                        total = total + score;
                        validScores++;
                    } else {
                        System.out.println("Invalid Quiz score: " + score);
                    }
                }
            } else {
                System.out.println("Error: Invalid Quizzes data.");
            }
            double quizAverage = 0;
            if (validScores > 0) {
                quizAverage = total / validScores;
            }
            category = input.next();
            numberOfScores = input.nextInt();
            total = 0;
            validScores = 0;

            if (category.equals("Exams") && numberOfScores > 0) {
                for (int i = 0; i < numberOfScores; i++) {
                    double score = input.nextDouble();

                    if (score >= 0 && score <= 100) {
                        total = total + score;
                        validScores++;
                    } else {
                        System.out.println("Invalid Exam score: " + score);
                    }
                }
            } else {
                System.out.println("Error: Invalid Exams data.");
            }
            double examAverage = 0;
            if (validScores > 0) {
                examAverage = total / validScores;
            }
            input.close();

            System.out.println("\nStudent: " + firstName + " " + lastName);
            System.out.println("\nCourse: " + courseName);
            System.out.println("\nCategory Results:");
            System.out.printf("  Projects (%d%%): average = %.2f%n",
                    weightOfProject, projectAverage);
            System.out.printf("  Quizzes (%d%%): average = %.2f%n",
                    weightOfQuiz, quizAverage);
            System.out.printf("  Exams (%d%%): average = %.2f%n",
                    weightOfExam, examAverage);

            Scanner keyboard = new Scanner(System.in);
            String choice = "";
            while (!choice.equalsIgnoreCase("Y")
                    && !choice.equalsIgnoreCase("N")) {

                System.out.print("\nApply +/- grading? (Y/N): ");
                choice = keyboard.nextLine();

                if (!choice.equalsIgnoreCase("Y")
                        && !choice.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }

            double overallAverage =
                    (projectAverage * weightOfProject / 100)
                    + (quizAverage * weightOfQuiz / 100)
                    + (examAverage * weightOfExam / 100);

            String letterGrade;

            if (overallAverage >= 90) {
                letterGrade = "A";
            } else if (overallAverage >= 80) {
                letterGrade = "B";
            } else if (overallAverage >= 70) {
                letterGrade = "C";
            } else if (overallAverage >= 60) {
                letterGrade = "D";
            } else {
                letterGrade = "F";
            }

            String finalGrade = letterGrade;

            if (choice.equalsIgnoreCase("Y")) {

                if (overallAverage >= 97) {
                    finalGrade = "A+";
                } else if (overallAverage >= 93) {
                    finalGrade = "A";
                } else if (overallAverage >= 90) {
                    finalGrade = "A-";
                } else if (overallAverage >= 87) {
                    finalGrade = "B+";
                } else if (overallAverage >= 83) {
                    finalGrade = "B";
                } else if (overallAverage >= 80) {
                    finalGrade = "B-";
                } else if (overallAverage >= 77) {
                    finalGrade = "C+";
                } else if (overallAverage >= 73) {
                    finalGrade = "C";
                } else if (overallAverage >= 70) {
                    finalGrade = "C-";
                } else if (overallAverage >= 67) {
                    finalGrade = "D+";
                } else if (overallAverage >= 63) {
                    finalGrade = "D";
                } else if (overallAverage >= 60) {
                    finalGrade = "D-";
                }
            }
            System.out.printf("%nOverall numeric average: %.2f%n",
                    overallAverage);
            System.out.println("Base letter grade: " + letterGrade);
            System.out.println("Final letter grade: " + finalGrade);
            if (defaultUsed) {
                System.out.println("Default configuration was used.");
            }
            PrintWriter output = new PrintWriter("grades_report.txt");
            output.println("========================================");
            output.println("   CMSC203 Project 1 - Grade Calculator");
            output.println("========================================");
            output.println();
            output.println("Student: " + firstName + " " + lastName);
            output.println();
            output.println("Course: " + courseName);
            output.println();
            output.println("Category Results:");
            output.printf("  Projects (%d%%): average = %.2f%n",
                    weightOfProject, projectAverage);
            output.printf("  Quizzes (%d%%): average = %.2f%n",
                    weightOfQuiz, quizAverage);
            output.printf("  Exams (%d%%): average = %.2f%n",
                    weightOfExam, examAverage);

            output.println();
            output.printf("Overall numeric average: %.2f%n",
                    overallAverage);
            output.println("Base letter grade: " + letterGrade);
            output.println("Final letter grade: " + finalGrade);

            if (defaultUsed) {
                output.println("Default configuration was used.");
            }
            output.close();
            System.out.println("\nSummary written to grades_report.txt");
            System.out.println("\nProgram complete. Goodbye!");
            keyboard.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: grades_input.txt could not be found.");
            System.out.println("Program ended.");
        }
    }
}
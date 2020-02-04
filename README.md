# temenuzhka-vuchova-employess
Project retrieving employees pair of worked longest period together

<b>EmployeesLongestPeriodWorkedWithoutUI</b> - containes main method which returns the pair of employees that have worked the most together on a common project.

When file is read line by one a HashMap is constructed for each project and all employees worked for a project. At this step is calculated time for which every worker worked on a project.Then we make Map which containes all pair of workers with their common time they worked for same project. 

Also take into account that if Person A work for 14 days and B work for 20 days on project then it should be counted as 14 days total common work on this project, not adding both project times together.If they are worked together on different projects and they both for one of the projects are worked for 10 days and for the other for 14 days then we need to sum the work ,total work 24 for all the projects.

<b>EmployeesLongestPeriodWorkedWithUI</b> -main method which realize same algorithm but with added UI - datagrid with data from file and result printed

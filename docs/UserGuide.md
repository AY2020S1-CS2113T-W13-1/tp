# User Guide

## Table of Contents

* <a href =#intro>1. Introduction </a>
* <a href =#start>2. Quick Start </a>
* <a href =#features>3. Features </a>      
   * <a href =#main-level>3.1 Main Level Features </a>
   * <a href =#subject-level>3.2 Subject Level Features </a>
   * <a href =#topic-level>3.3 Topic Level Features </a>
   * <a href =#other-feat>3.4 Other Features </a>
* <a href =#faq>4. FAQ </a>
* <a href =#summary>5. Command Summary </a>

## 1. Introduction <a name="intro"></a>

**revisED** aims to help students to revise their subjects through the creation of flashcards or tasks that have
to be completed. The users can store the tasks under different subjects and flashcards under different topics, which can be created under the
subjects. This application ensures that students would be
able to revise, even if they are doing it at the last minute.

This user guide provides an in-depth documentation on the installation process, the program features, and
the program usage to get you started.

Note the following symbols and formatting used in this document:

Symbols / Formatting|Meaning
------|------
📝️   **_NOTE:_** | Important or additional information
⚠️ **_WARNING:_** | Things to avoid or pay extra attention to
`Grey highlight` | Code or terms related to the code/application


## 2. Quick Start <a name="start"></a>

To setup the application,

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `revised.jar` [here](https://github.com/AY2021S1-CS2113T-W13-1/tp/releases).
1. Transfer the jar file to the folder of your choice.
1. Open a command prompt in the folder you just put the jar file in. Follow this [guide](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
if you are unsure how.
1. Run the command `java -jar revised.jar` to start the application.

You will see the logo of the application, as shown below, once u run it.

```
Hello from
                                    ___________
                                    |  __ |  _ \
 ____  ______      _____   ________ |  |__| | | |
|  __|/ __ \ \    / /| |  /  _____/ |   __| | | |
| |  |  __/ \ \__/ / | | /_____  /  |  |__| |_| |
| |   \___|  \____/  |_|/_______/   |_____|_____/
```

Subsequently, you can begin using the application!

## 3. Features <a name="features"></a>

This section elaborates on the features of **revisED** along with their usage. We further break this section down into several
subsections to better illustrate the design of the application.

* <a href =#main-level>3.1 Main Level Features </a>
* <a href =#subject-level>3.2 Subject Level Features </a>
* <a href =#topic-level>3.3 Topic Level Features </a>
* <a href =#other-feat>3.4 Other Features </a>

To give you some context, **revisED** is divided into 3 logical levels—**main**, **subject**, and **topic** levels. In the main level,
you can add/configure different subjects; in the subject level, you can add/configure different topics and tasks (under a subject); and in the topic
level, you can add/configure different flashcards (under a topic & subject). Continue reading to learn more about the features
in each level.

> 📝️️  **_NOTE:_** The format of the command, an example of usage, and a sample output will be presented in `grey boxes` 
> for each feature that involves the use of a command.


### 3.1 Main Level Features <a name="main-level"></a>
#### 3.1.1 Displaying all the available commands: `help`

Displays all available commands on the main level.
Use this command whenever you get confused  or need help to navigate the application.   

Format: `help`  
 
Example of usage:
```
help
```
Output:
```
________________________________________________________________________________________________________________________
help:          shows the list of commands available at the main level
add abc:       adds a subject called 'abc'
find abc:      finds all subjects containing the letters abc
list:          shows the list of all subjects
list all:      shows the tree of all subjects, topics, tasks and flashcards
delete 1:      deletes the 1st subject in the list.
subject abc:   enters the subject called abc, now you can create, find, list, delete and enter the topics of subject abc
quiz abc:      starts a quiz for all the flashcards present in all the topics of subject abc,
               answer the questions of the current flashcards to test your knowledge
results abc:   gives you the results of all attempted quizzes for abc subject
export:        exports all the data to a JSON file
bye:           exits the application
________________________________________________________________________________________________________________________
```

#### 3.1.2 Listing all subjects: `list` <a name="list"></a>

Prints a list of all subjects in the main list.

Format: `list`

Example of usage:
```
list
```
Expected output:

```
____________________________________________________________
Here are the subject(s) in your list:
1.CS1010
2.CS2040C
3.CG2027
4.CS2113T
5.CS2101
____________________________________________________________
```

#### 3.1.3 Listing all items: `list all` <a name="list"></a>

Prints a list of all items stored in the program.

Format: `list all`

+ The tree will show you that you are accessing the main level.

Expected output:

```
____________________________________________________________
Here's a list of all items:
(You are currently here)
├─ 1. math
│  Topics
│  ├─ 1. geometry
│  ├─ 2. ratio
│  └─ 3. speed
│     └─ 2 Flashcards
│  Tasks
│  ├─ 1. [E][?] test (at: 12:00 PM 12 Dec 2020)
│  └─ 2. [D][?] assignment (by: 12:00 PM 13 Dec 2020)
└─ 2. science
   Topics
   ├─ 1. ecology
   │  └─ 1 Flashcard
   └─ 2. plant reproduction
   Tasks
   └─ 1. [T][?] study for science

```

#### 3.1.4 Adding a subject: `add`

Adds a subject to the main list.

Format: `add [SUBJECT_NAME]`

Example of usage:
```
add CS2101
```
Expected output:

```
____________________________________________________________
Got it. I've added this subject:
  CS2101
Now you have 5 subjects in the list.
____________________________________________________________
```

#### 3.1.5 Deleting a subject: `delete`

Deletes the specified subject.

Format: `delete [SUBJECT_NUMBER]`

Example of usage:
```
delete 1
```
Expected output:

```
____________________________________________________________
 Noted. I've removed this subject:
   CS1010
 Now you have 4 subjects in the list.
____________________________________________________________
```
> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!
#### 3.1.6 Finding a subject: `find`

Searches the list of subject names for all subjects that contains the query.

Format: `find [QUERY]`

Example of usage:
```
find CS
```
Expected output:
```
____________________________________________________________
 Here are the matching subject(s) in your list:
CS2040C
CS2113T
CS2101
____________________________________________________________

```

#### 3.1.7 Accessing a subject: `subject`

Allows the user to access a pre-existing subject.

Format: `subject [SUBJECT_NAME]`

Example of usage:
```
subject CS2113T
```
Expected output:
```
____________________________________________________________
You are currently looking at the subject: CS2113T
____________________________________________________________
```

#### 3.1.8 Starting Subject Quiz: `quiz`

Starts a quiz for a particular subject. For more details, see <a href="#takeQuiz">takeQuiz</a>

Format: `quiz [SUBJECT_NAME]`
Example of usage: 
```
quiz CS2113T
```

Sample output: 
```
You are about to begin the quiz for maths.You have 2 minutes.
```

#### 3.1.8 Viewing results for quizzes on a subject: `results`

This command allows you to look at the results for a subject. For more details, see <a href="#result">result</a>.

Format: `results NAMEOFSUBJECT`

Example of usage: 
```
results CS2113T
```
Sample output: 
```
Quiz 1: 1.0/2.0 -- Pass
Quiz 2: 2.0/2.0 -- Excellent
```

#### 3.1.9 Exporting data: `export` <a name="export-command"></a>
This command exports all the data of the application to an external file. For more details, see <a href="#export">Exporting Data section</a>.

Format: `export`

Example of usage:
```
export
```

Sample output:
```
____________________________________________________________
Your data has been successfully exported to /home/guest/revised/export/data.json.
____________________________________________________________
```

#### 3.1.10 Saving and exiting: `bye` <a name="bye"></a>

Format: `bye`

Example of usage:
```
bye
```

Sample output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### 3.2 Subject Level Features (Herman)<a name = "subject-level"> </a>

Subject level features can only be used when the user is currently viewing a subject.
On this level, users are able to add and delete topics and tasks. 
Users are also able to access the topics listed in the current subject. 
For more details on topics, see section <a href="#topic-level">3.3 Topic Level Features</a>
This section describes the usage of commands that can be used to add, delete and list both topics and tasks. 

#### 3.2.1 Displaying all commands: `help` <a name = "subject-help"> </a>

Displays all available commands on the subject level.
Use this command whenever you need help to navigate the application. 

Format: `help` 
  
Example of usage:
```
help
```
Expected output:
```
________________________________________________________________________________________________________________________
help:                              shows the list of commands available at the subject level
add abc:                           adds a topic called 'abc' in the current subject
todo abc:                          adds a todo type task with the description 'abc'
deadline abc /by 12:00 13-11-2020: adds a deadline type task with description 'abc' with date/time of deadline
                                   as 12:00 AM 13 Nov 2020
event abc /at 01:00 21-11-2020:    adds an event type task with description 'abc' with date/time of event
                                   as 1:00 AM 21 Nov 2020
find abc:                          finds all topics and tasks containing 'abc' in the current subject
list:                              shows the list of all topics and tasks in the current subject
list all:                          shows the tree of all subjects, topics, tasks and flashcards
delete topic 1:                    deletes the 1st topic in the list of topics.
delete task 1:                     deletes the 1st task in the list of tasks.
done 1:                            marks the 1st task in the list of tasks as done
topic abc:                         enters the topic abc, now you can add, find, list and delete flashcards of topic abc
quiz abc:                          starts a quiz for all the flashcards of the topic abc,
                                   answer the questions of the prompted flashcards to test your knowledge
results abc:                       gives you the results of all attempted quizzes for abc topic
exit:                              exits the subject to return to the main screen, where you can work with subjects
________________________________________________________________________________________________________________________
```

#### 3.2.2 Listing all topics and tasks: `list` <a name="subject-list"></a>

Prints a list of all topics and tasks of the current subject. 
Use this command to view all objects under the current subject.
  
Format: `list`

Expected outcome:

```
____________________________________________________________
Here are the topic(s) under math: 
1.speed
____________________________________________________________
Here are the tasks(s) under math: 
1.[T][✘] laundry
2.[E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
3.[D][✘] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________

```
#### 3.2.3 Listing all items: `list all`

Prints a list of all items stored in the program.

Format: `list all`
+ The tree will show you which subject you are currently accessing.

Expected output:

```
____________________________________________________________
Here's a list of all items:
├─ 1. math (You are currently here)
│  Topics
│  ├─ 1. geometry
│  ├─ 2. ratio
│  └─ 3. speed
│     └─ 2 Flashcards
│  Tasks
│  ├─ 1. [E][?] test (at: 12:00 PM 12 Dec 2020)
│  └─ 2. [D][?] assignment (by: 12:00 PM 13 Dec 2020)
└─ 2. science
   Topics
   ├─ 1. ecology
   │  └─ 1 Flashcard
   └─ 2. plant reproduction
   Tasks
   └─ 1. [T][?] study for science
____________________________________________________________
```
#### 3.2.4 Adding a topic: `add` <a name="add-topic"></a>

Adds a topic to the current subject.

Format: `add [TOPIC_NAME]`
+ Adds a topic with a name of `[TOPIC_NAME]`

Example of usage: 
```
add speed
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this topic:
  speed
Now you have 1 topic in the list.
____________________________________________________________
```

#### 3.2.5 Deleting a topic: `delete topic` <a name="delete-topic"></a>

Deletes the topic from the current subject, given the topic number.

Format: `delete topic [TOPIC_NUMBER]`

+ Deletes the topic with the index number of `[TOPIC_NUMBER]`.
+ `[TOPIC_NUMBER]` must be a **positive integer**.
+ Topic `[TOPIC_NUMBER]` must **already exist in the topic list**.

Example of usage: 
```
delete topic 1
```

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this topic:
   speed
 Now you have 0 tasks in the list.
____________________________________________________________
```
> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!

#### 3.2.6 Finding a topic or task: `find` <a name="subject-find"></a>

Searches the current subject for all the topics and tasks that contains the query.

Format: `find [QUERY]`
+ searches all topics and tasks in the current subject with `[QUERY]` in its name and prints a list of them.
+ The search is case-sensitive. e.g. `math` will not find a topic with the name `MATH`.
+ The `find` command will only search the name of topics and tasks on this level.
+ Partial words will be matched. e.g. `mat` will find `mathematics`.

Example of usage: 

```
find laundry
``` 

Expected outcome:

```
____________________________________________________________
 Sorry! I could not find any topics with laund in the list.

 Here are the matching task(s) in your list:
[T][✓] laundry
____________________________________________________________
```

#### 3.2.7 Accessing a topic: `topic` <a name="access-topic"></a>

Enters a pre-existing topic.

Format: `topic [TOPIC_NAME]`
+ Access a pre-existing topic with a name of `[TOPIC_NAME]`.
+ This command is case-sensitive. e.g. `topic math` will not allow you to access a topic with the name `MATH`.
+ For more information on topic level commands, refer to 

Example of usage:
```
topic speed
```

Expected output:
```
____________________________________________________________
You are currently looking at the topic: speed
____________________________________________________________
```

#### 3.2.8 Adding a 'todo' task: `todo` <a name="todo"></a>

Adds a todo task.

Format: `todo [DESCRIPTION]`

Example of usage: 

```
todo laundry
```

Expected output:

```
____________________________________________________________
Got it. I've added this task:
	[T][✘] laundry
Now you have 4 tasks in the list.
____________________________________________________________
```

#### 3.2.9 Adding a 'deadline' task - `deadline` <a name="deadline"></a>

Adds a deadline task.

Format:

`deadline [DESCRIPTION] /by [TIME]`
+ A deadline must have a `[TIME]`. If you do not wish to set a time, add a <a href="#todo">todo</a> instead.

> 📝️️ **_NOTE:_** Add the time using the HH:MM DD-MM-YYYY format. 

Example of usage: 

```
deadline assignment /by 23:59 21-10-2020
```

Expected output:

```
____________________________________________________________
Got it. I've added this task:
  [D][✘] assignment (by: 11:59 PM 21 Oct 2020)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### 3.2.10 Adding an event - `event` - Add event <a name="event"></a>

Add an event task.

Format: `event [DESCRIPTION] /at [TIME]`
+ A deadline must have a `[TIME]`. If you do not wish to set a time, add a <a href="#todo">todo</a> instead.

> 📝️️ **_NOTE:_** Add the time using the HH:MM DD-MM-YYYY format. 

Example of usage: 

```
event tutorial /at 16:00 21-10-2020
```

Expected output:

```
____________________________________________________________
Got it. I've added this task:
  [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### 3.2.11 Deleting a task: `delete task`

Deletes a task from the current subject, given the task number.

Format: `delete task [TASK_INDEX]`

+ `[TASK_INDEX]` must be a **positive integer**.
+ Task `[TASK_INDEX]` must **already exist in the task list**.

Example of usage: 

```
delete task 2
```

Expected output:

```
____________________________________________________________
 Noted. I've removed this task:
   [E][✘] tutorial (at: 4:00 PM 21 Oct 2020)
 Now you have 2 tasks in the list.
____________________________________________________________
```

> ⚠️ **_WARNING:_** It is impossible to retrieve data deleted by this command. Make sure you do not need the data
> in this subject before you delete it!

#### 3.2.12 Marking a task as completed: `done`

Marks the specified task as done.

Format:

```
done [TASK_INDEX]
```

+ `[TASK_INDEX]` must be a **positive integer**.
+ Task `[TASK_INDEX]` must **already exist in the task list**.

Example of usage: 

```
done 2
```

Expected output:

```
____________________________________________________________
 Nice! I've marked this task as done:
   [D][✓] assignment (by: 11:59 PM 21 Oct 2020)
____________________________________________________________
```

#### 3.2.13 Starting Topic Quiz - `quiz` 

This type of quiz helps you to prepare for a particular topic. For more details, see section <a href =#takeQuiz>3.4.1 Taking quizzes</a>.

Format: `quiz [TOPIC_NAME]`

+ Starts a quiz of the a pre-existing topic the name `[TOPIC_NAME]`.
+ This command is case-sensitive. e.g. `quiz java` will not allow you to start a `Java` quiz.

Example of usage: 

```
quiz Java
```

Expected output: 
```
You are about to begin the quiz for Java.You have 1 minute.
```

#### 3.2.14 Viewing results for quizzes on a topic: `results`

Prints the previous results that you obtained for a quiz for a particular
topic. For more details, see <a href =#result>result</a>.

Format: `results [TOPIC_NAME]`

+ Displays the results of your previous quizzes of the topic the name `[TOPIC_NAME]`.
+ This command is case-sensitive. e.g. `results java` will not allow you to see you `Java` quiz results.

Example of usage: 

```
quiz Java
```

Expected output: 

```
Quiz 1: 1.0/2.0 -- Pass
Quiz 2: 2.0/2.0 -- Excellent

```

#### 3.2.14 Exiting the subject: `exit`

Exit the subjects and returns to the main level.

Format: `exit`

Expected output: 

```
____________________________________________________________
Going back to the main menu.
____________________________________________________________

```
> 📝️  **_NOTE:_** The `exit` command is not the same as the `bye` command.
> The `bye` command saves and closes the application, and can only be accessed on the main level.

### 3.3 Topic Level Features <a name="topic-level"> </a>

Flashcards can be added to take notes, in the form of questions and answers.
These flashcards can also be used to take quizzes, which is described in the next section.
This section describes the usage of commands that can be used to list all the available commands, 
and add, delete and list flashcards. 

> 📝️  **_NOTE:_** Flashcard commands can only be used when looking at a topic.

#### 3.3.1 `help` - Displaying all commands

Displays all available commands. Use this command whenever you get confused  or need help to navigate the application.   

Format: `help` 
  
Example of usage:
```
help
```
Expected output:
```
________________________________________________________________________________________________________________________
help:              shows the list of commands available at the topic level
add abc; def:      adds a flashcard with question 'abc' and answer 'def' in the current topic
list:              shows the list of all flashcards in the current topic
list all:          shows the tree of all subjects, topics, tasks and flashcards
delete 1:          deletes the 1st flashcard in the list
exit:              exits the topic to return to the subject level, where you can work with tasks and topics
________________________________________________________________________________________________________________________
```
> 📝️ ️  **_NOTE:_** Do not add extra spaces after or before help.

#### 3.3.2 `list` - List all flashcards

The list command prints a list of all flashcards of the topic you are currently viewing. 
This will help you to keep track of all the flashcards you have added 
and to take note of the number of the flashcard you might want to delete.
  
Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
Here are the flashcard(s) under speed: 
1.What is the formula for time?; Time = Distance/Speed.
2.What is the difference between speed and velocity? Speed, being a scalar quantity, is the rate at which an object covers distance. On the other hand, velocity being a vector quantity, is the rate at which the position changes.
```
> 📝️ ️️ **_NOTE:_** Do not add extra spaces after or before list.

#### 3.3.3 Listing all items: `list all`

Prints a list of all items stored in the program.

Format: `list all`
+ The tree will show you which topic you are currently accessing.

Expected output:

```
____________________________________________________________
Here's a list of all items:
├─ 1. math 
│  Topics
│  ├─ 1. geometry
│  ├─ 2. ratio
│  └─ 3. speed (You are currently here)
│     └─ 2 Flashcards
│  Tasks
│  ├─ 1. [E][?] test (at: 12:00 PM 12 Dec 2020)
│  └─ 2. [D][?] assignment (by: 12:00 PM 13 Dec 2020)
└─ 2. science
   Topics
   ├─ 1. ecology
   │  └─ 1 Flashcard
   └─ 2. plant reproduction
   Tasks
   └─ 1. [T][?] study for science
____________________________________________________________
```

#### 3.3.4 `add` - Adding a flashcard

The add command allows you to add a flashcard, under the topic you are currently viewing.
These flashcards can be added to take notes. 
They are added in the form of questions and answers.

Format:

`add` `[QUESTION]; [ANSWER]`

Example of usage: 
```
add How to calculate speed?; Speed=distance/time.
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this flashcard:
  How to calculate speed?; Speed=distance/time.
Now you have 3 flashcard in the list.
____________________________________________________________
```
> 📝️ ️  **_NOTE:_** Do not forget the semicolon, as it separates the question and the answer.

#### 3.3.5 `delete` - Deleting a flashcard

The delete command deletes the specified flashcard. 
This can be used if you make a mistake or no longer need a flashcard.
You can check the flashcard number from the list of flashcards, which is described next.

Format:

`delete` `[FLASHCARD NUMBER]`

Example of usage: 

`delete 1`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this flashcard:
   How to calculate speed?; Speed=distance/time.
 Now you have 2 flashcards in the list.
____________________________________________________________```
```

#### 3.3.6 `exit`

### 3.4 Other Features <a name="other-feat"></a>
This section expands on the features that are not specific to any part of the previous sections and features that
require further explanations.

#### 3.4.1 Taking quizzes <a name = "takeQuiz"> </a>
Once you have added the necessary flashcards, you can use the Quiz functionality to test yourself. This would
let you gauge how much you understand from the material. This will be beneficial if you are running out of time
to revise your own notes.
 
In this feature, the application prints the questions from the flashcards stored in the application. You need to complete the quiz within a
certain time limit. There are two types of quizzes :
* SubjectQuiz
* TopicQuiz

##### Subject quiz
This type of quiz helps you prepare for a particular subject.The application prints out the questions from the topics present in a subject.
You have 2 minutes to complete the quiz.

Format: `quiz NAMEOFSUBJECT`<br>
Example: `quiz Maths`

Output: `You are about to begin the quiz for maths.You have 2 minutes.`

##### Topic quiz
This type of quiz helps you to prepare for a particular topic. The application prints out all the questions that have been saved in the specific topic .
You have 1 minute to complete the quiz.

> 📝️ ️  **_NOTE:_** Ensure that you start a quiz for a subject/topic that has been added to the application 
> and has a flashcard. Else, the application will not start the quiz.

Once you have entered the type of quiz you want to begin, the application starts the quiz. You
are expected to answer these questions.

Format: `Question NAMEOFQUESTION`<br>
Example: `Question: What is distance` <br>

Once the quiz has been completed, the application prints out the scores that you obtained.
You will also be notified about the questions that you did not answer correctly. 

The score is printed in the following format:<br>
* If you get full marks : `Result:4/4 -- Excellent`<br>
* If you get enough marks to pass: `Result:2/4 -- Pass`<br>
* If you fail: `Result:0/4 -- Fail`<br>

The code below shows an example of a result that you can get after attempting a quiz.

<pre><code>____________________________________________________________
Result:2/4 -- Pass
____________________________________________________________
Here are the questions which you got wrong.
           
Question:distance 
Correct Answer: speed x time 
Your Answer: speed x time x time
____________________________________________________________
Question:velocity 
Correct Answer: displacement / time
Your Answer: none
____________________________________________________________</code></pre>

You can stop the quiz by entering `stop`. The application then prints the score.<br>
Format: `stop`<br>
Output:`The quiz has been stopped!`.
<pre><code>
____________________________________________________________
The quiz has been stopped!
____________________________________________________________
Result:1/2 -- Pass
____________________________________________________________
</code></pre>

#### 3.4.2 Viewing quiz results <a name ="result"> </a>
After completing a few number of quizzes, you would want to look at the results of the previous quizzes to see if
you have improved over the time. You can use the `results` functionality to check your results.
This can be done for the subjects or for the topics.

> 📝️ ️  **_NOTE:_** Ensure that at least one quiz has been attempted under the subject or topic.

> 📝️ ️  **_NOTE:_** The results for a subject is different from the results for a topic. The application only shows
>the result of a subject or topic that you requested for. Entering `results CS2113T` will not show you the results
>that you obtained for the topics in the CS2113T subject and vice-versa.  

#### 3.4.3 Storing data <a name="store"> </a>
When you exit the application, the data you have added to the program is automatically saved to the disk. 
The data is stored under the `data/` folder in the same folder where you run the application. 

> ⚠️ **_WARNING:_** The data (or any changes to the data) are not saved if the application is closed abnormally 
> (i.e. not via `bye` command).

Instead of storing all data in one file, **revisED** creates a folder hierarchy under `data/` following the logical structure of the
subjects and topics added. For example, if you add a `CS2113T` subject and a `Java` topic under it, a
`CS2113T/` folder will be created under the `data/` folder and an `Java` folder will be created under the `Maths/`
folder, as shown in figure 3.4.1 below. 

<pre>
revised.jar                     <em>--> <b>revisED</b> Application</em>
data                            <em>--> Data main folder</em>
└── CS2113T                     <em>--> CS2113T subject folder</em>
    ├── Java                    <em>--> Java topic folder under CS2113T subject</em>
    │   ├── topicResults.json
    │   └── flashcards.json
    ├── subjectResults.json
    └── tasks.txt
</pre>
<sup>***Figure 3.4.1** Sample directory structure created*</sup>


The details of the files that are created under each subject and topic folder will be explained below.

> 📝️️   **_NOTE:_**  The name of the subject and topic folders can be changed manually, and the changes will be reflected
> in the application the next time you launch it.

##### tasks.txt
One `tasks.txt` file will be created under each subject folder. This file contains the tasks (Todo, Event, Deadline) you
have added to a specific subject. An example of the file content is shown below.

<pre>
T | 1 | someTodoTask                                    <em>--> Todo task</em>
D | 0 | someDeadlineTask | 11:59 PM 20 Dec 2020         <em>--> Deadline task</em>
E | 0 | someEventTask | 1:00 PM 10 Nov 2020             <em>--> Event task</em>
</pre>
<sup>***Figure 3.4.2** Sample tasks.txt content*</sup>

The first column of data shows the type of task, where T corresponds to `Todo` task, D corresponds to `Deadline` 
task, and E corresponds to `Event` task. The second column shows if a task is completed, where 0 means not completed 
while 1 means completed. The third column shows the name of a task. Lastly, the fourth column shows the time and date of 
a deadline or event task. 

> ⚠️ **_WARNING:_** Although you can change the content of this file manually, 
> you are not advised to do so because a mismatch in the format will corrupt the data. 
> **If the data is corrupted, it will not be loaded by the application.**
> Make a copy of the file before making changes if you have to do so manually.

##### subjectResults.json / topicResults.json
One `subjectResults.json` will be created under each subject folder, and one `topicResults.json` will be created 
under each topic folder. `subjectResults.json` stores the quiz results you have obtained from doing quizzes under 
a subject, while `topicResults.json` stores the quiz results you have obtained from doing quizzes under a topic. 
Both of the files have the same content format, and an example of 
the file content is shown below.

<pre>
[
  {                                    <em>--> Result record 1</em>
    "score": 1.0,                      <em>--> Score obtained</em>
    "maxScore": 1.0,                   <em>--> Maximum score that can be obtained</em>
    "description": "Excellent"         <em>--> Result description</em>
  },
  {                                    <em>--> Result record 2</em>
    ...
  },
  ...
]
</pre>
<sup>***Figure 3.4.3** Sample subjectResults.json content*</sup>


##### flashcards.json
One `flashcards.json` file will be created under each topic folder. This file stores all the flashcard data you 
have added under a specific subject and topic. An example of the file content is shown below.

<pre>
[
  {                                            <em>--> Flashcard 1</em>
    "question": "x + y = 4. y = ? ",
    "answer": "4 - x"
  },
  {                                            <em>--> Flashcard 2</em>
    ...
  },
  ...
]
</pre>
<sup>***Figure 3.4.4** Sample flashcard.json content*</sup>

Each entry enclosed with the curly braces ({}) corresponds to one flashcard.

> ⚠️ **_WARNING:_** Although you can change the content of this file manually, 
> a mismatch in the format will corrupt the data. **If the data is corrupted, it will not be loaded by the application.** 
> Therefore, make a copy of the file before making changes if you have to do so.

#### 3.4.4 Loading data <a name="load"> </a>
Similar to <a href =#store>storing data</a>,
when you launch the application, the saved data is automatically loaded from the disk.

> 📝️   **_NOTE:_** Loaded subjects and topics will be sorted in alphabetical order, which can be seen when running
> <a href=#list>list</a> command. Flashcards, tasks, and results data, on the other hand, are not sorted and instead
> follow the added order.

> ⚠️ **_WARNING:_** If you have manually modified the contents of the files stored with wrong syntax, the files affected 
> will not be loaded (and, instead, empty data will be loaded) when the application launches. 

#### 3.4.5 Exporting data <a name= "export"> </a>
You can export all the data, including the quiz results and tasks, to a `json` file so that it can be imported into
other applications that understand the data. To export the data, run the <a href="#export-command">export command</a> 
when you are in the main level of the application. 

After running the command, the data will be exported to 
`export/data.json` under the same folder where **revisED** application resides. An example of the file content is shown 
below.

<pre>
[
  {                                                                  <em>--> Subject 1</em>
    "title": "CS2113T",                                                
    "topics": {
      "topics": [                                                    <em>--> Topics under CS2113T subject</em>
        {                                                            <em>--> Topic 1</em>
          "title": "Java",                                        
          "flashcards": <em>[same as the content of flashcards.json]</em>,    <em>--> Flashcards under Java topic</em>
          "results": {
            "resultList": <em>[same as the content of topicResults.json]</em> <em>--> Results under Java topic</em>
          }
        },
        ...
      ]
    },
    "tasks": {                                                       <em>--> Tasks under CS2113T subject</em>
      "taskList": [
        {                                                            <em>--> Task 1</em>
          "description": "someTodoTask",
          "isDone": false
        },
        {                                                            <em>--> Task 2</em>
          "dateTime": {
            "date": {
              "year": 2020,
              "month": 12,
              "day": 20
            },
            "time": {
              "hour": 23,
              "minute": 59,
              "second": 0,
              "nano": 0
            }
          },
          "description": "someDeadlineTask",
          "isDone": false
        },
        ...                                                       
      ]
    },                            
    "results": {
      "resultList": <em>[same as the content of subjectResults.json]</em>    <em>--> Results under CS2113T subject</em>
    }
  },
  ...                                                               <em>--> More subjects</em>
]
</pre>
<sup>***Figure 3.4.5** Sample data.json content*</sup>

Note that the content of the file follows the same logical structure as that
in the `data/` folder.

> 📝️ ️ **_NOTE:_** **Importing** of the exported file is **currently not supported** as it is meant to be read by other 
> applications. Nevertheless, the feature may be implemented in the future versions if it is highly requested.

#### 3.4.6 Sorting tasks  <a name= "sort"> </a>
The tasks in the application are sorted according to their deadlines. Tasks which are due soon are placed at the front
of the list, while tasks which are due later are placed at the rear. `Todo` tasks are placed at the end of the list since
they do not have any deadlines.

Suppose you use the `list` command and get this output:
<pre><code>
Here are the tasks(s) under maths: 
1. [D][✘] homework (by: 6:00 PM 6 Nov 2020)
2. [E][✘] marathon (at: 6:00 PM 9 Nov 2020)
</code></pre>

Adding the following deadline by `deadline project /by 18:00 07-11-2020` and using the `list` command would yield this output.
<pre><code>
Here are the tasks(s) under maths: 
1. [D][✘] homework (by: 6:00 PM 6 Nov 2020)
2. [D][✘] run again (by: 6:00 PM 7 Nov 2020)
3. [E][✘] marathon (at: 6:00 PM 9 Nov 2020)
</code></pre>


## 4. FAQ <a name = "faq"> </a>
This section answers some common questions that you may have about **revisED**.

**_Q:_ I moved the jar file to another location. Will my previous data be erased automatically?**<br>
**_A:_** Do not worry. You previous data will still be avaiable in the `data/` folder.

**_Q:_ I accidentally saved a task as an event, when it is a deadline. Is there any method to change the type of the task?**<br>
**_A:_** Unfortunately, no. You will have to delete the event using the `delete task [TASK_INDEX]`to delete the task, and then 
add the task back using the `deadline [DESCRIPTION] /by [TIME]` command.

**_Q:_ I stopped a quiz without completing it. Can I resume it?**<br>
**_A:_** No. You will have to start a new quiz.

**_Q:_ Is there any limit to the number of topics/subjects that I can store in my application?**<br>
**_A:_** No. You can store unlimited number of topics/subjects.   

**_Q:_ A file named `revisED0.log` appears in the folder after I run the application. What is it? Can I delete it?**<br>
**_A:_** This is the log file that logs down the activities of the application when you are running it. In the case where
you find any bugs or the application crashes, you can send us a bug report
[here](https://github.com/AY2021S1-CS2113T-W13-1/tp/issues) along with the log file to help us with solving the bugs. 
Nevertheless, you can delete it if you want to, and it does not affect the behavior of the application in any way.

## 5. Command Summary <a name="summary"></a>
A summary of all the commands available is shown in the table below.

Action|Examples
------|------
Add subject|`add math` (while in main menu)
Add topic|`add speed` (while looking at a subject)
Add todo|`todo revise for test` 
Add deadline| `deadline assignment /by 23:59 21-10-2020`
Add an event| `event tutorial /at 16:00 21-10-2020`
Find tasks| `find math`
Complete task|`done 4`
Delete task|`delete task 4`
Delete topic| `delete topic 4`
Change to the specific subject |`subject maths`
Change to the specific topic |`topic speed `
Start a quiz for a subject| `quiz maths`
Start a quiz for a topic| `quiz speed`
View results for a subject | `results maths`
View results for a topic | `results speed`
Export data | `export`
Exit a topic or subject | `exit`
Exit application|`bye`
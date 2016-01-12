A Multi-threaded Cypher Breaker


Overview

The field of cryptanalysis is concerned with the study of cyphers,
having as its objective the identification of weaknesses within a
cryptographic system that may be exploited to convert encrypted
data (cypher-text) into unencrypted data (plain text). Whether
using symmetric or asymmetric techniques, cryptanalysis assumes no
knowledge of the correct cryptographic key or even the
cryptographic algorithm being used.

Assuming that the cryptographic algorithm is known, a common
approach for breaking a cypher is to generate a large number of
keys, decrypt a cypher-text with each key and then examine the
resultant plain text. If the text looks similar to English, then
the chances are that the key is a good one. The similarity of a
given piece of text to English can be computed by breaking the text
into fixed - length substrings, called n-grams, and then comparing
each substring to an existing map of n-grams and their frequencies.
This process does not guarantee that the outputted answer will be
the correct plain text, but should give a good approximation that
may well be the right answer.


Author

Student Name: Andrejs Lavrinovics
Student ID: g00196984


Project Content

* reilfence.jar that contain Runner class with main method
* src folder with source code
* README.txt
* design.png with application’s UML diagram
* docs directory that contains java docs
* build.xml file used for and builder


How to run

Cypher Breaker is a console application. Thus you need to use
command line tool to run it. Also latest version of jdk or jre
must to be installed on your machine.
Using command line you need to navigate into directory that
contain reilfence.jar file. Make sure that 4gram.txt and
overview.txt is placed on the same directory.

For example – reilfence.jar is in the desktop/project folder,
then 4gram.txt and overview.txt must be in the same directory.
For linux command line you need to type cd ~/desktop/project, for
Windows cmd type cd %userprofile%/desktop/project

Use java –cp ./railfence.jar ie.gmit.sw.Runner command to run the
application.


Functionality

The goal of application is to find a proper key that will decrypt
cypher text. Therefore first of all you will be ask to provide a
plain text. There are two options that you can use – Input the
file name containing plain text or prompt the plaintext. Make sure
that appropriate file is placed in the same folder with
reilfence.jar file if you had chosen input file name option.
Text automatically will be transformed into uppercase text and all
spaces will be removed.

Then you will be asked for entering a key to encrypt text. The
application will allow to enter only valid key. The valid key is a
number between 2 and number that equal to length of the text
divided by 2.

Assumed that program doesn’t know the key you entered. Application
creates a number of threads that equals to number of available
potential keys and decrypts plain text using each key. Then app
brakes down the decrypted text on grams and searches each in the
hash map that contains set of grams. Every gram in hash map refer
to a score as a key > value pairs. Total score is calculated by
summing all scores that matched to appropriate grams.

All values: key, decrypted text and total score are wrapping into
bunch as Result object and pushing it into blocking queue. On the
other end of queue consumer thread collecting the result bunches
(one per time) and holding only one with higher result.

Finally program prints the decrypted text and appropriate key.


Methodology

Blocking queue – is designed to be used for producer-consumer
queues. Blocking Queue implementations are thread-safe. All queuing
methods achieve their effects atomically using internal locks or
other forms of concurrency control.

A Blocking Queue does not intrinsically support any kind of "close"
or "shutdown" operation to indicate that no more items will be
added. The needs and usage of such features tend to be
implementation-dependent. For example, a common tactic is for
producers to insert special end-of-stream or poison objects that
are interpreted accordingly when taken by consumers.

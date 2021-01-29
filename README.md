# Line Server

## How does the system work?
The system is based on an index that has as its key the line index and as its value the line coordinates, the start position and end position inside the file.
The `FileLineIndex` abstraction is implemented in this case using a javax.jcache provider, namely [ehcache](https://ehcache.org).

Using ehcache allows having files with a large amount of lines to index because it can be configured to be multi-tiered using the heap, the off-heap and the disk.

The file reading is delegated on an abstraction `FileReader` which is, in this case, implemented as a `FileChannelReader`. Using a java.nio.channel allows this solution to be multi-threaded and if more than one request wants to read different parts of the file they do not block each other.

For the rest service the it uses [Spring Boot](https://spring.io/projects/spring-boot) with its `spring-boot-starter-web`.
  
## How will the system perform with a 1 GB file? a 10 GB file? a 100 GB file?
The system will perform well even with big files. It can have performance degradation but it's not due to the size of the file itself but due to the number of lines that may make the index grow and span to the disk.

## How will the system perform with 100 users? 10000 users? 1000000 users?
It will depend on the operations, if they are reading the same part of the file. It will perform reasonably well for 100 user. If this was needed in a real production environment, with real use cases, probably it would have to be scaled. This solution allows it: just reimplement the indexing strategy, and/or the reader with some other more robust solutions.

## Documentation, websites, papers
Mainly the documentation for Spring, ehcache, and - of course - Java.

## Third-party libraries anf other tools 
- [ehcache](https://ehcache.org), because I needed something to seamlessly manage an index that can be very big, too big to fit in-memory
- [spring boot](https://spring.io/projects/spring-boot), to have everything need to have a rest service working without too much configuration.
- [junit](https://junit.org/junit5/) and [hamcrest](http://hamcrest.org/JavaHamcrest/) for unit testing
- [gradle](https://gradle.org), as my preferred building tool

## How long did it take to finish this exercise? 
I would say 3 days - more on thinking on the solution that coding.

If I had unlimited time, the first thing I would do would be stress test my solution; if needed, maybe implement a couple more different approach solutions to the problem.
And refactor, refactor, refactor.

I am quite happy with my code, but there is no such thing as perfect code. There is a lot of space for refactoring. Since I have a nice unit test base I would (or will) have no trouble cleaning this code more and more.
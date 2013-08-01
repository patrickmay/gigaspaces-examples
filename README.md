Overview
========
This is a collection of GigaSpaces examples for common use cases.


Environment
===========
These examples have been developed and tested under version 9.6 on Mac OS X 10.8.4 with Java 7.


Examples
========

Simple Cache
------------
This example shows how to use GigaSpaces as a simple distributed cache, not unlike memcached.

Stateless Worker
----------------
This example shows how to implement a service that runs in a separate GigaSpaces processing unit and accesses all partitions of a distributed space, processing requests and returning responses.

Stateful Worker
---------------
This example shows how to implement a service that runs co-located with each partition of a distributed space, processing requests and returning responses.

Map Reduce
----------
This example shows how to implement the map-reduce pattern in GigaSpaces, using co-located workers.

Remote Executor
---------------
This example shows how to implement distributed services managed by GigaSpaces and using the space as a transport mechanism.

Mirror Service
--------------
This example shows how to use the GigaSpaces' mirror service to reliably and asynchronously persist changes in the space to a database.

Examples Structure
==================

Files and Directories
---------------------
Each of the example subdirectories has a common structure:
- `README` describes the example and provides detailed instructions on how to build and run it.
- `build.properties` usually just extracts the GigaSpaces home
environment variable, which should be set to `$JSHOMEDIR`, for use by `ant`.
- `build.xml` generally provides the `ant` targets `build`, `clean`, and `rebuild`.
- `bin` contains shell scripts for starting a GigaSpaces infrastructure and deploying the example.
- `shared` contains classes that are used by more than one other component of the example.

Depending on the example, there are other common elements:
- `*-space` contains a `pu.xml` file for configuring a space with the appropriate co-located services.
- `feeder` contains the source for writing objects into the space to trigger the example.

The remaining directories typically contain the code for the services that make up the example.

Building
--------
In most of the examples, the source code for the services and shared classes is not stored in the file format corresponding to the package name.  Instead, the `ant` build script creates a `classes` directory that is the target for the output of `javac`, a `lib` directory to hold the `.jar` files, and a `deploy` directory that has the usual Spring structure.

This approach is a little unusual, but it makes it easier to browse the code through GitHub.


Contact
=======
Questions and comments can be sent to patrick.may@mac.com.

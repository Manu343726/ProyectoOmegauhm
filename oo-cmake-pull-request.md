
# Integrate oo-cmake with [biicode](www.biicode.com) C/C++ dependency management

Hi! I'm Manu Sánchez, form the biicode team.  

I'm working intensively with CMake scripts and I found your library so useful to me. Maps, function calls, etc; features that make feasible writing complex CMake scripts without going crazy!

biicode is a file-based dependency manager for C and C++, in the same spirit of Java's Maven or Python's pip. The tool uses CMake under the hood to process builds, so to setup a biicode *block** you should play a bit with `CMakeLists.txt` files. *A block is our unit of code sharing, thing of it as a package.*    
Here's an example, part of the `CMakeLists.txt` file of our [Box2D block](https://www.biicode.com/erincatto/erincatto/box2d/master/10):

``` cmake
IF(BIICODE)
    INCLUDE(${CMAKE_HOME_DIRECTORY}/biicode.cmake)
    INIT_BIICODE_BLOCK()
    ADD_DEFINITIONS(-DGLEW_STATIC)
    SET(BII_LIB_SYSTEM_DEPS )
    ADD_BIICODE_TARGETS()
    target_include_directories(${BII_LIB_TARGET} PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
ELSE()
    cmake_minimum_required(VERSION 2.6)

    project(Box2D)

    if(UNIX)
        set(BOX2D_INSTALL_BY_DEFAULT ON)
    else(UNIX)
        set(BOX2D_INSTALL_BY_DEFAULT OFF)
    endif(UNIX)

    option(BOX2D_INSTALL "Install Box2D libs, includes, and CMake scripts" ${BOX2D_INSTALL_BY_DEFAULT})
    option(BOX2D_INSTALL_DOC "Install Box2D documentation" OFF)
    ...
ENDIF()
```

Is exactly the same `CMakeLists.txt` from the original library, just adding a couple of toggles to make it work with biicode.  
To use Box2D, you only have to `#include` it in your C/C++ code, biicode does everything else (Download, setup build, etc). [Check our docs](http://docs.biicode.com/) for more info.

Since version 2.0 we support dependency management of CMake scripts, via `include()` directives. Your library was very useful to me, so I created a block to deploy and reuse it easily across all my biicode blocks. I hope you like it.


## oo-cmake changes




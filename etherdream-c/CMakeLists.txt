cmake_minimum_required(VERSION 3.10)
project(etherdream_c C CXX)

set(CMAKE_C_STANDARD 99)

if (UNIX AND NOT APPLE)
    include_directories(/usr/lib/jvm/default-java/include/)
    include_directories(/usr/lib/jvm/default-java/include/linux)
else()
    include_directories(/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/include)
    include_directories(/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/include/darwin)
endif()

add_library(EtherdreamJNI SHARED protocol.h etherdream.c etherdream.h spacefiller_etherdream_EtherdreamDevice.h spacefiller_etherdream_EtherdreamDevice.cpp)
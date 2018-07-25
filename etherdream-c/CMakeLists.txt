cmake_minimum_required(VERSION 3.10)
project(etherdream_c C CXX)

set(CMAKE_C_STANDARD 99)

include_directories(/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/include)
include_directories(/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/include/darwin)

add_library(EtherdreamJNI SHARED protocol.h etherdream.c etherdream.h spacefiller_etherdream_EtherdreamDevice.h spacefiller_etherdream_EtherdreamDevice.cpp)
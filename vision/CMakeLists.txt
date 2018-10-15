cmake_minimum_required(VERSION 3.12)
project(LabVision)

set(CMAKE_CXX_STANDARD 11)
find_package( OpenCV REQUIRED )
set(SOURCE_FILES main.cpp)
add_executable(LabVision ${SOURCE_FILES})
target_link_libraries(LabVision ${OpenCV_LIBS} )

include_directories(${OpenCV_INCLUDE_DIRS})
target_link_libraries( LabVision ${OpenCV_LIBS} )




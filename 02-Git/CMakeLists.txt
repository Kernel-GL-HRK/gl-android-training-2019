project(minesweeper)
cmake_minimum_required(VERSION 2.8.)

include_directories(include)

set(SRC_LIST
  src/main.cpp
  src/field.cpp)

add_executable(${PROJECT_NAME} ${SRC_LIST})

set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 17)

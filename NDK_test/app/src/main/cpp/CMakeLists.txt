
cmake_minimum_required(VERSION 3.22.1) # 사용할 CMake 최소 버전 설정

project("ndk_test") # 프로젝트 이름 설정

set(CMAKE_CXX_STANDARD 17)             # C++17 표준 사용 설정
set(CMAKE_CXX_STANDARD_REQUIRED ON)    # C++17 필수 사용 강제

add_library(
        ${CMAKE_PROJECT_NAME}          # "ndk_test"라는 이름으로
        SHARED                         # 공유 라이브러리로 빌드
        native-lib.cpp)                # native-lib.cpp 파일 포함

find_library(
        log-lib                        # 안드로이드 로그 라이브러리 검색
        log
)

target_link_libraries(
        ${CMAKE_PROJECT_NAME}
        android                        # Android 네이티브 API 지원
        log                            # __android_log_print 등 사용을 위한 log 라이브러리
        c++_shared                     # shared 방식의 C++ STL 사용
        c++abi)                        # C++ 런타임에 필요한 ABI 라이브러리 링크
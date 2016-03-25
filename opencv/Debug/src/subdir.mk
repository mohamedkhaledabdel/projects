################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/a2.cpp \
../src/a2_trial.cpp \
../src/a3.cpp \
../src/opencv.cpp 

OBJS += \
./src/a2.o \
./src/a2_trial.o \
./src/a3.o \
./src/opencv.o 

CPP_DEPS += \
./src/a2.d \
./src/a2_trial.d \
./src/a3.d \
./src/opencv.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I/usr/local/include/opencv -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '



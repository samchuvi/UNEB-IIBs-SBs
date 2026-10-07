# Execution Order
1. Static block of the main class (once)
2. main() method starts
3. Static block of Student (once, when Student is first used)
4. Instance block (every object)
5. Constructor (every object)

Order: STATIC BLOCK -> INSTANCE BLOCK -> CONSTRUCTOR

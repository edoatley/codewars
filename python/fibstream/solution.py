# You're going to provide a needy programmer a utility method that generates an infinite amount of sequential fibonacci numbers.
# 
# to do this write a 'generator' starting with 1
# 
# A fibonacci sequence starts with two 1s. Every element afterwards is the sum of the two previous elements. See:
# 
# 1, 1, 2, 3, 5, 8, 13, ..., 89, 144, 233, 377, ...

def all_fibonacci_numbers():
    n = 1
    while True:  # Infinite loop
        yield fib(n)  # Yield the current number and pause
        n += 1    # Increment n

# Add memoization to the fibonacci function
fib_cache = {}

def fib(x):
    if x in fib_cache:
        return fib_cache[x]
    
    if x <= 2:
        return 1 
    
    fib_value = fib(x - 1) + fib(x - 2)
    fib_cache[x] = fib_value
    return fib_value

# Some nice other solutions:

def all_fibonacci_numbers():
    a, b = 0, 1
    while 1:
        yield b
        a, b = b, a + b
        
def all_fibonacci_numbers(a: int = 0, b: int = 1):
    yield b
    yield from all_fibonacci_numbers(b, a + b)
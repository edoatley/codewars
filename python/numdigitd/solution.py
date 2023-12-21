# Found this helpful for the Maths https://www.tutorialspoint.com/computer_logical_organization/number_system_conversion.htm#:~:text=Step%201%20%E2%88%92%20Divide%20the%20decimal,divide%20by%20the%20new%20base.

digits='0123456789abcdefghijklmnopqrstuvwxyz'

def count_digit(number, digit, base=10, from_base=10):
    # here we want to count the number of 'digit' in the 'number' after converting to 'base' from the 'from_base' 
    print(f'count_digit({number}, {digit}, {base}, {from_base})')
    
    if from_base != 10:
        decimal = other_base_to_base_10(number, from_base)
    else:
        decimal = int(number)
    
    print(f'{decimal=}')
    rebased = base_10_to_new_base(decimal, base)
    print(f'{rebased=}')
    
    return rebased.count(digit)


def other_base_to_base_10(number: str, old_base: int) -> int:
    digit_sum = 0
    length = len(number)
    
    for i in range(length):
        m =  old_base ** (length - 1 - i)
        digit_sum += (digits.index(number[i]) * m)
    
    return digit_sum
 

def base_10_to_new_base(base_10_number: int, new_base: int) -> str:
    rebased_digits = []
    result = base_10_number
    
    if result == 0: 
        return '0'
    
    while result > 0:
        # print(f'divmod({result}, {new_base})')
        result, mod = divmod(result, new_base)
        rebased_digits.append(digits[mod])
    
    return "".join([str(i) for i in rebased_digits[::-1]])

# NOTE:
# There are actually a ton of library based solutions to this!
# for example:

# this could be in place of other_base_to_base_10
def base_10_from_base_n(number, n):
    return int(number, n)

# this is just way better!
from numpy import base_repr
def count_digit_lib(number, digit, base=10, from_base=10):
    return base_repr(int(number, from_base), base).lower().count(digit)

# As is this (apparently better backwards compatible with python 2)
from gmpy2 import mpz
def count_digit(number, digit, base=10, from_base=10):
    return mpz(number, base=from_base).digits(base).count(digit)

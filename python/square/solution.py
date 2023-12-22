from math import sqrt

def is_square(n) -> bool:    
    return n >= 0 and sqrt(n).is_integer()
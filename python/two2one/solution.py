def longest(s1:str, s2:str) -> str:
    return ''.join(sorted(set(s1 + s2)))
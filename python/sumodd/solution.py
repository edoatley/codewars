def row_sum_odd_numbers(n):
    start = n ** 2 - n + 1
    return sum([x for x in range(start, (start + n * 2), 2)])
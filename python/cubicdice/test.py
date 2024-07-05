import codewars_test as test
from solution import rolldice_sum_prob

test.describe("Example Tests")
def assertFuzzyEquals(actual, expected, msg=""):
    import math
    # max error
    merr = 1e-10
    if expected == 0:
        inrange = math.fabs(actual) <= merr
    else:
        inrange = math.fabs((actual - expected) / expected) <= merr
    if msg == "":
        msg = "Expected value near {:.10f}, but got {:.10f}"
        msg = msg.format(expected, actual)
    return test.expect(inrange, msg)
    
assertFuzzyEquals(rolldice_sum_prob(11, 2), 0.055555555555)
assertFuzzyEquals(rolldice_sum_prob(8, 2), 0.13888888889)
assertFuzzyEquals(rolldice_sum_prob(8, 3),0.0972222222222)
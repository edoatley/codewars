# Three 1's => 1000 points
# Three 6's =>  600 points
# Three 5's =>  500 points
# Three 4's =>  400 points
# Three 3's =>  300 points
# Three 2's =>  200 points
# One   1   =>  100 points
# One   5   =>   50 point
def score(dice):
    score = 0
    for num, count in [(x, dice.count(x)) for x in set(dice)]:
        if count >= 3:
            score += num * 100

        if num == 1:
            score += (count * 100)
            if count > 2:
                score += 700
        if num == 5:
            score += (count * 50)
            if count > 2:
                score += 350
        if count == 3:
            if num in [2, 3, 4, 6]:
                score += (num * 100)
    
    return score


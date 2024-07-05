def rolldice_sum_prob(sum_, dice_amount):
    print(f'rolldice_sum_prob({sum_}, {dice_amount})')
    
    if (6 * dice_amount) < sum_:
        print(f'rolldice_sum_prob({sum_}, {dice_amount}) - sum_ too BIG')
        return 0
    
    return count_proba(sum_, dice_amount) / (6 ** dice_amount)
    

def count_proba(sum_, dice):
    # print(f'count_proba({sum_}, {dice})')
    if sum_ < 1:
        return 0
    elif dice == 1:
        return int(1 <= sum_ <= 6)
    else:
        return sum(count_proba(sum_ - n, dice-1) for n in range(1,7))
    
if __name__ == "__main__":
    rolldice_sum_prob(11,2)
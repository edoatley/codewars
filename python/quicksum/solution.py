def quicksum(packet: str):
    sum = 0
    if packet[0].isupper() and packet[-1].isupper():
        for i,c in enumerate(packet):
            if c.isupper():
                sum +=((i+1) * (ord(c) - ord('A') + 1))
    return sum

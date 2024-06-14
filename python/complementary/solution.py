bp = {
    'C' : 'G',
    'G' : 'C',
    'A' : 'T',
    'T' : 'A',
}

def DNA_strand(dna):
    return "".join([bp[x] for x in dna])

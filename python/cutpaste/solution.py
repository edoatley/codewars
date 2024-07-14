from typing import List

# words are groups of alphabetic chars
# Non-alphabetic chars are considered to be punctuation and spaces (there is no distinction)
# Repeated letters within words are not errors -- address is not meant to be adres
# The cutting never breaks words words apart
# Fragments of only punctuation and/or spaces are never double-pasted
# repeated punctuation is possible -- Hey!!! is not meant to be Hey!
# repeated spaces is possible -- abc   def is not meant to be abc def
# Only detect adjacent double pasting. We don't care if the same cut text might be elsewhere
# Your code must treat only first repetitions as double pastes.
# You want to remove the longest repetitions of the shortest sequences (e.g.  double in the example, not  double double))
# Pasting occurs strictly left-to-right only appending to your current text

def tokenize(text: str) -> List[str]:
    tokens = []
    alpha_token = text[0].isalnum()
    current_token = ''

    for i, c in enumerate(text):
        c_alpha = c.isalnum()
        if (alpha_token and c_alpha) or (not alpha_token and not c_alpha):
            current_token = current_token + c
        else:
            tokens.append(current_token)
            current_token = c
            alpha_token = c_alpha
            
    tokens.append(current_token)

    return tokens

def fix_duplicate_tokens(tokens: List[str]) -> List[str]:
    updated_tokens = []
    # for token in tokens
    for i, t in enumerate(tokens):
        if not t.isalnum():
            updated_tokens.append(t)
            continue
        # scan ahead for a match to the token
        print(f'Scanning ahead for {t=}, {i=}')
        j = [j for j, tj in enumerate(tokens[i+1:]) if t == tj]
        # if not gound continue
        if len(j) == 0:
            updated_tokens.append(t)
        else:    
            # if found see if there is a match with the next token at each end until you find the duplicate token or they do not match
            steps = 0
            print(f'{j=}')
            while (j + steps) < len(tokens) and (i + steps) < j:
                if tokens[i+steps] == tokens[j+steps]:
                    print(f'{tokens[i+steps]} == {tokens[j+steps]}')
                else:
                    steps = -1
                    break
                steps += 1
            if steps > 0:
                print(f'Potential error: {tokens[i: j+steps]}')
                
        
    return tokens


def fix_cut_paste(text):
    tokens = tokenize(text)
    print(tokens)
    fix_duplicate_tokens(tokens)
    
    
    
if __name__ == "__main__":
    text = "Here is some piece of text piece of text that was was accidentally double double pasted."
    fix_cut_paste(text)
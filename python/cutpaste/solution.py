from typing import List
import logging, sys
from autologging import logged, traced, TRACE

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

@logged
@traced
class CutPaste:
        
    def __init__(self, text : str):
        self.__log.info("initialized")
        self.tokens = self.tokenize(text)
        self.errors = []
    
    def tokenize(self, text: str) -> List[str]:
        tokens = []
        alpha_token = text[0].isalnum()
        current_token = ''

        for c in text:
            c_alpha = c.isalnum()
            if (alpha_token and c_alpha) or (not alpha_token and not c_alpha):
                current_token = current_token + c
            else:
                tokens.append(current_token)
                current_token = c
                alpha_token = c_alpha
                
        tokens.append(current_token)

        return tokens

    def find_duplicate_tokens(self) -> List[str]:
        errors = []
        # for token in tokens
        for i, t in enumerate(self.tokens):
            # scan ahead for a match to the token
            dupe_indices = [index for index, token in enumerate(self.tokens[i+1:]) if t == token]
            
            # if none found or non-alpha token skip
            if t.isalnum() and len(dupe_indices) > 0:   
                # if found see if there is a match with the next token at each end until you find the duplicate token or they do not match
                for x in dupe_indices:
                    if self.could_be_duplicate_tokens(i, x):
                        errors.append((i, x))

        return errors

    def could_be_duplicate_tokens(self, i, j):
        if i >= j:
            return False
        
        steps = 1
        while (i + steps) < len(self.tokens) and (i + steps) < j:
            if self.tokens[(i + steps)] != self.tokens[(j + steps)]:
                return False
        
        return True

    def fix_cut_paste(self):
        self.errors = self.find_duplicate_tokens()
        logging.info(f'Found errors: {self.errors}')
        # logic to remove errors
        return ''.join(self.tokens)
    
        
if __name__ == "__main__":
    logging.basicConfig(
        level=TRACE, stream=sys.stderr,
        format="%(levelname)s:%(filename)s,%(lineno)d:%(name)s.%(funcName)s:%(message)s")
    cut_paste = CutPaste("Here is some piece of text piece of text that was was accidentally double double pasted.")
    cut_paste.fix_cut_paste()
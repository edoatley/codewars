from typing import List
import logging, sys

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

class CutPaste:
        
    def __init__(self, text : str):
        # self.__log.info("initialized")
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
            logging.debug(f'Checking for dupes with {i=}, {t=}')
            dupe_indices = [index for index, token in enumerate(self.tokens) if t == token and index > i ]
            logging.debug(f'dupes {dupe_indices=}')
            
            # if none found or non-alpha token skip
            if t.isalnum() and len(dupe_indices) > 0:   
                # if found see if there is a match with the next token at each end until you find the duplicate token or they do not match
                for x in dupe_indices:
                    logging.debug(f'Checking dupe index {x=} ({i=})')
                    if self.could_be_duplicate_tokens(i, x):
                        errors.append((i, x))
        self.errors = errors
        return errors

    def could_be_duplicate_tokens(self, i, j):
        logging.debug(f'could_be_duplicate_tokens({self.tokens[i]}, {self.tokens[j]})')
        if i >= j:
            return False
        
        steps = 1
        while (i + steps) < len(self.tokens) and (i + steps) < j:
            if self.tokens[(i + steps)] != self.tokens[(j + steps)]:
                return False
            
            steps += 1
        
        return True

    def process_errrors(self):
        delete_indexes = []
        for a, b in self.errors:
            i = 0
            while True:
                if self.tokens[a+i] == self.tokens[b+i] and (b+i) < len(self.tokens):
                    i += 1
                else:
                    break
            left = self.tokens[a:a+i]
            right = self.tokens[b:b+i]
            logging.info(f'L: {left} / R {right}')
            if left == right:
                for x in range(b, b+i):
                    delete_indexes.append(x)
        
        
        logging.info(delete_indexes)
        for idx, ele in enumerate(self.tokens):
            in_del = idx in delete_indexes
            logging.info(f'{idx=}, {ele=}, {in_del=}')
           
        self.tokens = [ele for idx, ele in enumerate(self.tokens) if idx not in delete_indexes]
            

    def fix_cut_paste(self):
        self.find_duplicate_tokens()
        self.process_errrors()
        logging.info(f'Found errors: {self.errors}')
        # logic to remove errors
        return ''.join(self.tokens)
    
        
if __name__ == "__main__":
    logging.basicConfig(stream=sys.stderr,level="INFO",
        format="%(levelname)s:%(filename)s,%(lineno)d:%(name)s.%(funcName)s:%(message)s")
    cut_paste = CutPaste("Here is some piece of text piece of text that was was accidentally double double pasted.")
    result = cut_paste.fix_cut_paste()
    logging.info(f"Result : {result}")
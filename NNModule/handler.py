import random as rn


class NN:
    def __init__(self):
        rn.seed(12241)

    def classifySegment(self, seg):
        # NN Implemented here
        return rn.randint(0, 12)


class Phoneme:
    def __init__(self):
        self.phoneme = 0


class Handler:

    # """Version 1"""
    def __init__(self):
        self.inputQueue = []
        self.network = NN()

    def classifySegment_V1(self):
        return self.network.classifySegment(self.inputQueue.pop(0))  # between 1 and 12

    def getPhonetic_V1(self):
        phonetic = Phoneme()
        phonetic.phoneme = self.classifySegment_V1()
        return phonetic

    def addSegment_V1(self, segment):
        self.inputQueue.append(segment)
        return

    def trainAI_V1(self):
        return
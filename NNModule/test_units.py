import unittest
import random
import SegmentNode
import handler

def getSegment():
    amps = []
    for n in range(0, 500):
        amps.append(random.uniform(-1, 1))
    return amps

print('Starting Unit Testing...\n\n')

nodeHandler = handler.Handler()

class UnitTester(unittest.TestCase):

    def testInitialization(self):
        print('\tTESTING INITIALIZATION')
        self.assertTrue(nodeHandler != None)
        
    def testSegments(self):
        print('\tTESTING SEGMENTS OUTPUT')
        nodeSegment = SegmentNode.SegmentNode(getSegment())
        nodeHandler.addSegment_V1(nodeSegment)
        test = nodeHandler.getPhonetic_V1().phoneme
        print(test)
        self.assertTrue(0 <= test <= 11)
        
    def testSegments(self):
        print('\tTESTING SEGMENTS OUTPUT')
        nodeSegment = SegmentNode.SegmentNode(getSegment())
        nodeHandler.addSegment_V1(nodeSegment)
        test = nodeHandler.getPhonetic_V1().phoneme
        print(test)
        self.assertTrue(0 <= test <= 11)
        
    def testSegments(self):
        print('\tTESTING SEGMENTS OUTPUT')
        nodeSegment = SegmentNode.SegmentNode(getSegment())
        nodeHandler.addSegment_V1(nodeSegment)
        test = nodeHandler.getPhonetic_V1().phoneme
        print(test)
        self.assertTrue(0 <= test <= 11)
        
    def testSegments(self):
        print('\tTESTING SEGMENTS OUTPUT')
        nodeSegment = SegmentNode.SegmentNode(getSegment())
        nodeHandler.addSegment_V1(nodeSegment)
        test = nodeHandler.getPhonetic_V1().phoneme
        print(test)
        self.assertTrue(0 <= test <= 11)

unittest.main()
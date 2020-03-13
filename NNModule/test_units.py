import unittest
import random
import SegmentNode

def getSegment():
    amps = []
    for n in range(0, 500):
        amps.append(random.uniform(-1, 1))
    return amps

print('Starting Unit Testing...\n\n')

nodeSegment = SegmentNode.SegmentNode(getSegment())



print(nodeSegment.getsomething())


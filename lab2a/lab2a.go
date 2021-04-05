package main

import (
	"math/rand"
	"sync"
	"time"
)

type Briefcase struct {
	mutex sync.Mutex
	_isEmpty bool // if true, then there are no more tasks
	numOfRows int // the overall number of tasks
	nextRow int // the next task to do
}

// specifies that there are no more tasks
func (briefcase *Briefcase) close() {
	briefcase.mutex.Lock()
	briefcase._isEmpty = true
	briefcase.mutex.Unlock()
}

func (briefcase *Briefcase) isEmpty() bool {
	briefcase.mutex.Lock()
	defer briefcase.mutex.Unlock()

	return briefcase._isEmpty
}

func main() {
	var waitGroup sync.WaitGroup

	/* The number of groups of bees.
	 * Each goroutine represents a group of bees.
	 */
	numOfGoroutines := 5

	// the forest's size
	numOfRows := 15
	numOfColumns := 25

	forest := make([][]int, numOfRows)
	for i := range forest {
		forest[i] = make([]int, numOfColumns)
	}

	rand.Seed(time.Now().UnixNano())
	forest[rand.Intn(numOfRows)][rand.Intn(numOfColumns)] = 1 // places the bear at a random location in the forest

	waitGroup.Add(numOfGoroutines)

	for i := 0; i < numOfGoroutines; i++ {
		// goroutines
	}

	waitGroup.Wait()
}
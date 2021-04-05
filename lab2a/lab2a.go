package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Briefcase struct {
	mutex sync.Mutex
	isEmpty bool // If true, then there are no more tasks.
	numOfRows int // the overall number of tasks
	nextRow int // the next task to do
}

// Specifies that there are no more tasks.
func (briefcase *Briefcase) close() {
	briefcase.mutex.Lock()
	briefcase.isEmpty = true
	briefcase.mutex.Unlock()
}

// Returns the next task to do or if there are no more tasks returns -1.
func (briefcase *Briefcase) getNextRow() int {
	briefcase.mutex.Lock()
	defer briefcase.mutex.Unlock()

	if briefcase.isEmpty {
		return -1
	}

	nextRow := briefcase.nextRow
	briefcase.nextRow++

	if briefcase.nextRow >= briefcase.numOfRows {
		briefcase.isEmpty = true
	}

	return nextRow
}

func searchBear(goroutineId int, forest [][]int, briefcase *Briefcase, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()

	for {
		rowIndex := briefcase.getNextRow() // gets a task

		if rowIndex == -1 { // if there are no more tasks
			fmt.Printf("[%v]: Nothing to do. The group of bees [%v] returns to the hive.\n", goroutineId, goroutineId)
			return
		}

		for columnIndex, value := range forest[rowIndex] {
			if value == 1 { // if the bear is found
				fmt.Printf("[%v]: The bear is found at the location (%v, %v).\n", goroutineId, rowIndex, columnIndex)
				briefcase.close() // reports that a bear is found
				return
			}
		}
		fmt.Printf("[%v]: The bear is not found in row %v.\n", goroutineId, rowIndex)
	}
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

	briefcase := Briefcase{numOfRows: len(forest)}

	rand.Seed(time.Now().UnixNano())
	randomRowIndex := rand.Intn(numOfRows)
	randomColumnIndex := rand.Intn(numOfColumns)

	forest[randomRowIndex][randomColumnIndex] = 1 // places the bear at a random location in the forest
	fmt.Printf("A bear is placed at the location (%v, %v).\n", randomRowIndex, randomColumnIndex)

	fmt.Println("Here is the forest:")
	for i := range forest {
		fmt.Println(forest[i])
	}

	waitGroup.Add(numOfGoroutines)

	for i := 0; i < numOfGoroutines; i++ {
		go searchBear(i, forest, &briefcase, &waitGroup)
	}

	waitGroup.Wait()
}
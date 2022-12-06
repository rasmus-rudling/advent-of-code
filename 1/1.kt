import java.io.BufferedReader
import java.io.FileReader

class Elf(var calories: MutableList<Int>) {
  var totCalories: Int = 0
      get() {
        return this.calories.reduce { acc, i -> acc + i }
      }
}

fun getElfWithMostCalories(elfs: List<Elf>): Elf {
  var elfWithMostCalories = elfs[0]
  for (elf in elfs) {
    if (elf.totCalories > elfWithMostCalories.totCalories) {
      elfWithMostCalories = elf
    }
  }
  return elfWithMostCalories
}

fun getTopThreeElfsWithMostCalories(elfs: List<Elf>): List<Elf> {
  var topThreeElfsWithMostCalories = mutableListOf<Elf>()
  var elfsWithMostCalories = elfs.toMutableList()
  for (i in 1..3) {
    var elfWithMostCalories = getElfWithMostCalories(elfsWithMostCalories)
    topThreeElfsWithMostCalories.add(elfWithMostCalories)
    elfsWithMostCalories.remove(elfWithMostCalories)
  }

  return topThreeElfsWithMostCalories
}

fun summarizeElfsCalories(elfs: List<Elf>): Int {
  var sum = 0
  for (elf in elfs) {
    sum += elf.totCalories
  }
  return sum
}

fun main(args: Array<String>) {
  val reader = BufferedReader(FileReader("data.txt"))

  var line = reader.readLine()
  var elfs = mutableListOf<Elf>();
  var currentElf = Elf(mutableListOf<Int>());

  while (line != null) {
    if (line.isBlank()) {
      elfs.add(currentElf)
      currentElf = Elf(mutableListOf<Int>());
    } else {
      val calories = line.toInt()
      currentElf.calories.add(calories)
    }

    line = reader.readLine()
  }
  var elfWithMostCalories = getElfWithMostCalories(elfs)

  var topThreeElfsWithMostCalories = getTopThreeElfsWithMostCalories(elfs)
  var totalCaloriesOfTopThreeELfs = summarizeElfsCalories(topThreeElfsWithMostCalories)

  println(totalCaloriesOfTopThreeELfs)
}

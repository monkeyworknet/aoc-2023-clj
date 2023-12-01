(ns aoc-2023.day1
  (:require [clojure.string :as str]))

;; On each line in data remove all non numeric characters
;; and then add the first and last digit together
;; if there is only one digit you add it to itself.

;; sample data should return answer 142

(def sample-data
  "1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet")

(def full-data (slurp "/Users/monkeywork/dev/clj/aoc-2023/resources/day1-input.txt"))


(defn get-num-from-string [s]
  (->> s
       (filter #(Character/isDigit %))
       (map #(Integer. (str %)))))

(defn get-num-from-vector [vs]
  (map get-num-from-string vs))

(defn create-vector-of-strings [s]
  (str/split-lines s))

(defn combine-first-and-last [n] 
  (+ (* 10 (first n)) (last n)))


(defn solve1
  [s]
  (->> s
       (create-vector-of-strings)
       (get-num-from-vector)
       (map #(combine-first-and-last %))
       (reduce +)))

(solve1 sample-data)
(solve1 full-data)

(def sample-data2 
     "two1nine
   eightwothree
   abcone2threexyz
   xtwone3four
   4nineeightseven2
   zoneight234
   7pqrstsixteen"
   )

;; Now some numbers may be spelled out as words
;; Sample Data answer is 281

(def digit-map 
  {"1" 1 "2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9 "0" 0
   "one" 1 "two" 2 "three" 3 "four" 4 "five" 5 "six" 6 "seven" 7 "eight" 8 "nine" 9 "zero" 0
   })

(def digit-list 
  ["1" "2" "3" "4" "5" "6" "7" "8" "9" "0"
   "one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "zero"])

(defn first-num [s]
  (->> (filter (partial str/includes? s) digit-list)
       (apply min-key (partial str/index-of s))))

(defn last-num [s]
  (->> (filter (partial str/includes? s) digit-list)
       (apply max-key (partial str/last-index-of s))))

(defn first-and-last [s]
  (let [f (first-num s)
        l (last-num s)]
    (Integer. (str (digit-map f) (digit-map l)))))

(defn solve2
  [s]
  (->> s
       (create-vector-of-strings)
       (map first-and-last)
       (reduce +)))

(solve2 sample-data)
(solve2 sample-data2)
(solve2 full-data)
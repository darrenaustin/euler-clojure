(ns euler.p096
  (:use [clojure.set :only [union]]
        [euler.numeric :exclude [digits]]
        [euler.util]
        [clojure.string :only [join]]))

;; Sudoku solver based on Norvig's Python implementation
;; (http://norvig.com/sudoku.html).
;; This is easy enough to solve on my own, but I found his solution
;; very elegant and wanted to see what it might look like translated
;; to Clojure.

(defn cross [A B] (for [a A b B] (str a b)))

(def digits #{\1 \2 \3 \4 \5 \6 \7 \8 \9})
(def empties #{\0 \.})
(def valid-entries (union digits empties))
(def rows "ABCDEFGHI")
(def cols "123456789")
(def squares (sort (cross rows cols)))

(defn map-squares [f] (reduce #(assoc % %2 (f %2)) {} squares))

(def unit-list (concat 
                (for [c cols] (set (cross rows [c])))
                (for [r rows] (set (cross [r] cols)))
                (for [rs ["ABC" "DEF" "GHI"] cs ["123" "456" "789"]] (set (cross rs cs)))))
(def units (map-squares #(filter (fn [unit] (unit %)) unit-list)))
(def peers (map-squares #(disj (apply union (units %)) %)))

(def empty-puzzle (map-squares (constantly digits)))

(declare assign eliminate display-puzzle)


(defn reduce-while-true [f val coll]
  (if-let [s (seq coll)]
    (if-let [new-val (f val (first s))]
      (recur f new-val (rest s)))
    val))

(defn assign [puzzle s d]
  (reduce-while-true #(eliminate %1 s %2) puzzle (disj (puzzle s) d)))

(defn eliminate [puzzle s d]
  (let [candidates (puzzle s)]
    (if-not (candidates d)
      puzzle
      (when-not (= (count candidates) 1)
        (let [candidates-left (disj candidates d)
              puzzle (assoc-in puzzle [s] candidates-left)
              puzzle (if (= 1 (count candidates-left))
                       (reduce-while-true #(eliminate %1 %2 (first candidates-left)) puzzle (peers s))
                       puzzle)]
          (println)
          (display-puzzle puzzle)
          (reduce-while-true
           (fn [puzzle unit]
             (let [d-squares (filter #((puzzle %) d) unit)]
               (println d-squares "for" d)
               (when-not (zero? (count d-squares))
                 (if (= (count d-squares) 1)
                   (assign puzzle (first d-squares) d)
                   puzzle))))
           puzzle
           (units s)))))))

(defn search [puzzle]
  (when puzzle
    (let [num-candidates (comp count puzzle)]
      (if (every? #(= (num-candidates %) 1) squares)
        puzzle
       (let [square-least-candidates 
              (first (sort-by num-candidates (filter #(< 1 (num-candidates %)) squares)))]
          (some identity
                (for [d (puzzle square-least-candidates)]
                  (search (assign puzzle square-least-candidates d)))))))))

(defn parse-puzzle [puzzle-text]
  (let [known (zipmap squares (map #(if (digits %) %) (filter valid-entries puzzle-text)))]
    (reduce-while-true 
     (fn [puzzle s]
       (if-let [d (known s)]
         (assign puzzle s d)
         puzzle))
     empty-puzzle
     squares)))

(defn read-puzzles []
  (mapv (fn [data] [(first data) (parse-puzzle (apply concat (rest data)))])
       (partition 10 (.split (slurp "data/sudoku.txt") "\n"))))
  
(defn display-puzzle [puzzle]
  (let [width (inc (apply max (for [s squares] (count (puzzle s)))))
        separator (join "+" (repeat 3 (join (repeat (* 3 width) "-"))))
        sep-cols (set "36")
        sep-squares #{"C9" "F9"}]
    (doseq [s squares :let [c (second s) values (join (puzzle s))]]
      (print (centered-string values width))
      (when (sep-cols c) (print "|"))
      (when (= c \9) (println))
      (when (sep-squares s) (println separator)))))

(defn first-corner-num [puzzle]
  (number (apply str (map #(first (puzzle %)) ["A1" "A2" "A3"]))))

(defn solved? [puzzle]
  (and puzzle (every? #(= (sort digits) (sort (mapcat puzzle %))) unit-list)))

;; TODO: finish this
;(defn solution {:answer 24702} []
(defn solution []
;  (sum (map (comp first-corner-num search) (read-puzzles)))
  (doseq [[name puzzle] (read-puzzles)]
    (let [solved (search puzzle)]
      (assert (solved? solved) (str "Not actually solved: " name))
      (println name (if-not solved " - NOT SOLVED" ""))
      (display-puzzle (if solved solved puzzle)))))

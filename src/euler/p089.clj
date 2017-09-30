(ns euler.p089
  (:use euler.numeric))

(defn read-roman [s]
  (let [decimal-val {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000}]
    (loop [dec 0, vals (map decimal-val s)]
      (if (empty? vals)
        dec
        (if (and (> (count vals) 1) (< (first vals) (second vals)))
          (recur (+ dec (- (second vals) (first vals))) (drop 2 vals))
          (recur (+ dec (first vals)) (rest vals)))))))


(defn roman [n]
  (let [roman-vals [[1000 "M"]
                    [900 "CM"] [500 "D"] [400 "CD"] [100 "C"]
                    [90 "XC"]  [50 "L"]  [40 "XL"]  [10 "X"]
                    [9 "IX"]   [5 "V"]   [4 "IV"]   [1 "I"]]]
    (loop [roman [], n n, vals-left roman-vals]
      (if (zero? n)
        (apply str roman)
        (let [[d r] (first vals-left)]
          (if (>= n d)
            (recur (conj roman r) (- n d) vals-left)
            (recur roman n (rest vals-left))))))))

(defn solution {:answer 743} []
  (let [romans (.split (slurp "data/roman.txt") "\n")]
    (sum (map #(- (count %) (count (roman (read-roman %)))) romans))))

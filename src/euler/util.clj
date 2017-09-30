(ns euler.util
  (:use [clojure.string :only [join]]))

(defn words-from [filename]
  (map #(.replace % "\"" "") (.split (slurp filename) ",")))

(defn collect [cols]
  (mapcat identity cols))

(defn indexed-map
  ([keys] (indexed-map keys 0))
  ([keys start-value]
     (apply hash-map (interleave keys (iterate inc start-value)))))

(defn compare-seqs [s1 s2]
  (if (empty? s1)
    (compare nil (first s2))
    (let [comparison (compare (first s1) (first s2))]
      (if (zero? comparison)
        (compare-seqs (rest s1) (rest s2))
        comparison))))

(defmacro timings [n expr]
  `(let [results# (for [x# (range ~n)
                        :let [start# (System/nanoTime)
                              ret# ~expr]]
                    [ret# (- (System/nanoTime) start#)])
         times# (map (fn [result#] (/ (second result#) 1000000000.0)) results#)
         high# (apply max times#)
         low# (apply min times#)
         average# (if (> ~n 2)
                    (/ (- (reduce + times#) high# low#) (- ~n 2))
                    (/ (reduce + times#) ~n))]
     {:average average#, :result (ffirst results#), :high high#, :low low#, :times times#}))

(defmacro avg-time [n expr]
  `(let [results# (timings ~n ~expr)]
     (results# :average)))

(defn centered-string [s width]
  (assert (>= width (count s)))
  (let [space (- width (count s))
        right-padding (/ space 2)
        left-padding (if (odd? space) (dec right-padding) right-padding)]
    (apply str (join (repeat left-padding " ")) s (join (repeat right-padding " ")))))
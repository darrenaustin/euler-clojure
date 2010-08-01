(ns dma.euler.p014
  (:use clojure.contrib.def))

(defn-memo chain-length [n]
  (if (= n 1)
    1
    (let [next (if (even? n) (/ n 2) (inc (* 3 n)))]
      (inc (chain-length next)))))

(defn solution []
  (reduce (fn [[max-length max-start] next]
            (let [length (chain-length next)]
              (if (> length max-length)
                [length next]
                [max-length max-start])))
          [0 0]
          (range 1 1000000)))

(ns dma.euler.p058
  (:use [dma.euler numeric primes]))

(defn corners [n]
  (let [delta (dec n)
        lower-right (square n)
        lower-left (- lower-right delta)
        upper-left (- lower-left delta)
        upper-right (- upper-left delta)]
    [upper-right upper-left lower-left lower-right]))

(defn prime-corners [n]
  (count (filter prime? (corners n))))

(defn solution {:answer 26241} []
  (loop [n 3, primes (prime-corners 3), diagonals 5]
    (if (< (/ primes diagonals) 1/10)
      n
      (recur (+ n 2)
             (+ primes (prime-corners (+ n 2)))
             (+ diagonals 4)))))

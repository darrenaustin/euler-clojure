(ns dma.euler.numeric)

(defn natural [max] (range 1 (inc max)))
(defn sum [col] (reduce + col))
(defn product [col] (reduce * col))
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn div? [n d] (zero? (rem n d)))

(defn whole-nums [] (iterate inc 0))
(defn natural-nums [] (rest (whole-nums)))

(defn fibs
  ([] (fibs 0 1))
  ([a b] (lazy-seq (cons a (fibs b (+ a b))))))

(defn triangle-nums
  ([] (triangle-nums 0 1))
  ([s n] (lazy-seq (cons (+ s n) (triangle-nums (+ s n) (inc n))))))

(defn take-while-< [max col]
  (take-while #(< % max) col))

(defn take-between [min max col]
  (take-while #(< % max) (drop-while #(< % min) col)))

;; (defn digit->int [d]
;;   (- (int d) (int \0)))

;; (defn digits [n]
;;   (map digit->int (str n)))

;; (defn number [digits]
;;   (read-string (apply str digits)))

;; (defn num-digits [n]
;;   (count (str n)))

;; (defn palindrome? [num]
;;   (let [digits (seq (str num))]
;;         (= digits (reverse digits))))

;; (defn n-digits [n]
;;   (range (int (. Math pow 10 (dec n)))
;;                  (int (. Math pow 10 n))))

;; (defn exp [x n]
;;   (reduce * (take n (repeat x))))

;; (defn factorial [n]
;;   (reduce * (range 2 (inc n))))

;; (defn find-first [pred col]
;;   (first (filter pred col)))

;; (defn proper-divisors [n]
;;   (if (= n 1) [1]
;;     (loop [ds [1]
;;                    current 2
;;                    end (int (. Math sqrt n))]
;;           (cond
;;            (> current end) ds
;;            (div? n current)
;;              (let [factor (/ n current)]
;;                    (recur (into ds (if (= factor current) [factor] [factor current]))
;;                                   (inc current)
;;                                   end))
;;            :else (recur ds (inc current) end)))))

;; (defn divisors [n]
;;   (conj (proper-divisors n) n))

;; (defn sum-divisors [n]
;;   (sum (proper-divisors n)))

;; (defn perfect? [n]
;;   (= n (sum-divisors n)))

;; (defn abundant? [n]
;;   (> (sum-divisors n) n))

;; (defn deficient? [n]
;;   (< (sum-divisors n) n))

;; (defn amicable? [a]
;;   (let [b (sum-divisors a)]
;;         (and (not= a b)
;;                  (= a (sum-divisors b)))))

;; (defn intersect [as bs]
;;   "Intersect two sorted sequences of numbers"
;;   (cond
;;    (nil? as) nil
;;    (nil? bs) nil
;;    (= (first as) (first bs))
;;      (conj (intersect (rest as) (rest bs)) (first as))
;;    (> (first as) (first bs))
;;      (intersect as (rest bs))
;;    :else
;;          (intersect (rest as) bs)))

;; (defn gcd [a b]
;;   "Geatest common divisor between a and b."
;;   (apply max (intersect (sort (divisors a))
;;                                                 (sort (divisors b)))))

;; (defn lcm [a b]
;;   "Least common multiple for a and b"
;;   (/ (* a b) (gcd a b)))

;; (defn combinations [& cs]
;;   (reduce (fn [vs c] (mapcat #(map conj vs (repeat %)) c)) [[]] cs))

;; (defn permutations [s]
;;   (if (= 1 (count s))
;;         (list s)
;;         (for [head s
;;                   tail (permutations (disj (set s) head))]
;;           (cons head tail))))

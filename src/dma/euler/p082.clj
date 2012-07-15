(ns dma.euler.p082
  (:use [dma.euler.matrix]))

(defn column [m x] (map #(nth % x) m))

(defn paths-left-in-dir [m pos dir]
  (loop [paths [] p pos sum 0]
    (let [[ny nx] (map + p dir)]
      (if-let [n (get-in m [ny nx])]
        (recur (conj paths (+ sum n (get-in m [ny (inc nx)]))) [ny nx] (+ sum n))
        paths))))

(defn min-path-left [m [y x]]
  (apply min (concat (paths-left-in-dir m [y x] [-1 0])
                     [(get-in m [y (inc x)])] 
                     (paths-left-in-dir m [y x] [1 0]))))

(defn min-paths-for-column [m x]
  (vec
   (for [y (range (height m))]
     (min-path-left m [y x]))))

(defn propagate-min-path-sums [m]
  (reduce (fn [m x]
            (let [min-paths (min-paths-for-column m x)]
              (reduce (fn [m y] (update-in m [y x] + (nth min-paths y)))
                      m (range (height m)))))
          m (reverse (range (dec (width m))))))
  
(defn solution {:answer 260324} []
  (apply min (column (propagate-min-path-sums large-matrix) 0)))

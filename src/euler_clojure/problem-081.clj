(ns euler-clojure.problem-081
  (:use euler-clojure.matrix))

(defn migrate-bottom [matrix]
  (let [bottom (dec (height matrix))]
    (loop [x (- (width matrix) 2)
           m matrix]
      (if (< x 0)
        m
        (recur (dec x)
               (set-value m [x bottom]
                          (+ (value m [x bottom]) (value m [(inc x) bottom]))))))))

(defn migrate-right [matrix]
  (let [right (dec (width matrix))]
    (loop [y (- (height matrix) 2)
           m matrix]
      (if (< y 0)
        m
        (recur (dec y)
               (set-value m [right y]
                          (+ (value m [right y]) (value m [right (inc y)]))))))))

(defn migrate-path [matrix]
  (loop [y (- (height matrix) 2) m matrix]
    (if (< y 0)
      m
      (recur (dec y)
             (loop [x (- (width matrix) 2) m m]
               (if (< x 0)
                 m
                 (let [pos [x y]
                       current (value m pos)
                       down (value m [x (inc y)])
                       right (value m [(inc x) y])]
                   (recur (dec x) (set-value m pos (+ current (min down right)))))))))))

(defn solution [matrix]
  (value (-> matrix migrate-bottom migrate-right migrate-path) [0 0]))

;; An alternate solution using a transient matrix
(defn solution! [matrix]
  (let [m (transient-matrix matrix)
        w (width matrix)
        h (height matrix)
        bottom (dec h)
        right (dec w)]
    (doall (for [x (range (- w 2) -1 -1)]
             (set-value! m [x bottom] (+ (value m [x bottom]) (value m [(inc x) bottom])))))
    (doall (for [y (range (- h 2) -1 -1)]
             (set-value! m [right y] (+ (value m [right y]) (value m [right (inc y)])))))
    (doall (for [y (range (- h 2) -1 -1)
                 x (range (- w 2) -1 -1)]
             (let [pos [x y]
                   current (value m pos)
                   down (value m [x (inc y)])
                   right (value m [(inc x) y])]
               (set-value! m pos (+ current (min down right))))))
    (value m [0 0])))

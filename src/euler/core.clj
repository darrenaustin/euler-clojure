(ns euler.core
  (:gen-class)
  (:import java.io.FileNotFoundException))

(def problem-nums (range 1 (inc 299)))

(defstruct solution :prob-num :solution :answer :time)

(defn format-solution [solution]
  (when solution
    (let [secs (/ (solution :time) 1000000000.0)]
      (format "Problem %3d: %12s (%5.2f sec%s) [ %s ]"
              (solution :prob-num)
              (solution :solution)
              secs
              (if (> secs 60) " - TOO SLOW " "")
              (if-let [answer (solution :answer)]
                (if (= answer (solution :solution))
                  "correct"
                  "INCORRECT")
                "unknown")))))

(defn problem-ns-sym [n]
  (symbol (format "euler.p%03d" n)))

(defn problem-fn-sym [n]
  (symbol (str (problem-ns-sym n) "/solution")))

(defn find-solution-fn [n]
  (let [prob-fn-name (problem-fn-sym n)]
    (try
      (require [(problem-ns-sym n) :reload true])
      (catch FileNotFoundException ex))
    (resolve prob-fn-name)))

(defn problem-answer [n]
  (if-let [solution-fn (find-solution-fn n)]
    (if-let [solution-meta (meta (resolve (problem-fn-sym n)))]
      (solution-meta :answer))))

(defn solve [n]
  (if-let [solution-fn (find-solution-fn n)]
    (let [answer (problem-answer n)
          start-time (. System nanoTime)
          result (solution-fn)]
      (struct solution n result answer
              (- (. System nanoTime) start-time)))))

(defn solve-if-known [n]
  (when (problem-answer n)
    (println (format-solution (solve n)))))

(defn euler-unsolved []
  (for [n problem-nums :when (not (problem-answer n))] n))

(defn euler
  ([] (dorun (map solve-if-known problem-nums)))
  ([n] (println (format-solution (solve n)))))

(defn -main [& args]
  (euler))

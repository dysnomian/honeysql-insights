(ns honeysql-insights.format
  (:refer-clojure :exclude [format])
  (:require [clojure.string :as string]
            [honeysql.format :refer :all]))

(defmethod format-clause :select [[_ fields] sql-map]
  (str "SELECT "
       (when (:modifiers sql-map)
         (str (space-join (map (comp string/upper-case name)
                               (:modifiers sql-map)))
              " "))
       (comma-join (map to-sql fields))))

(defmethod format-clause :fn [field]
  (str field))

(defmethod format-clause :timeseries [_]
  (str _ "TIMESERIES"))

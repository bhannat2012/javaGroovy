package com

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User: Akhil Shastri
 * Date: 10/18/13
 * Time: 4:54 PM
 */

@EqualsAndHashCode(includes=['id'])
@ToString(includeNames=true)
class Account {
    Integer id
    String name
    Date timeStamp
}

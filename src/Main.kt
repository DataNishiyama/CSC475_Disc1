//Human War Class
class HumanWarrior(var name: String) {
    var health: Int = 100
    private var attackPower: Int = 20

    fun attack(target: Monster) {
        val damageDealt = (1..attackPower).random()
        println("$name attacks the ${target.name} for $damageDealt damage!")
        target.takeDamage(damageDealt)
    }

    fun takeDamage(damage: Int) {
        health -= damage
        if (health <= 0) {
            println("$name has been defeated!")
        } else {
            println("$name has $health health remaining.")
        }
    }
}

//Monster Class
open class Monster(var name: String, var health: Int) {
    open fun takeDamage(damage: Int) {
        health -= damage
        if (health <= 0) {
            println("$name has been defeated!")
        } else {
            println("$name has $health health remaining.")
        }
    }

    open fun attack(target: HumanWarrior) {
    }
}

//Type Monster Goblin
class Goblin(name: String = "Goblin", health: Int = 50) : Monster(name, health) {
    override fun attack(target: HumanWarrior) {
        val damageDealt = (1..10).random()
        println("$name attacks for $damageDealt damage!")
        target.takeDamage(damageDealt)
    }

    override fun takeDamage(damage: Int) {
        super.takeDamage(damage)
        if (health > 0) {
            println("$name attacks back!")
        }
    }
}

//Main
fun main() {
    val player = HumanWarrior("Anthony")
    val goblin = Goblin()

    println("Hello, ${player.name}!")
    println("You encounter a Goblin!")
    println("Prepare for battle!")

    while (player.health > 0 && goblin.health > 0) {
        println("\n${player.name}'s turn:")
        player.attack(goblin)
        if (goblin.health <= 0) break

        println("\n${goblin.name}'s turn:")
        goblin.attack(player)
    }

    //Win Conditions
    if (player.health <= 0) {
        println("\n${player.name} has fallen in battle. The Goblin wins!")
    } else {
        println("\nThe Goblin lies defeated! Victory is yours, ${player.name}!")
    }
}

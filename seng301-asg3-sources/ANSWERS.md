# SENG301 Assignment 1 (2020) - student answers


## Task 1 - Identify the patterns

### Pattern 1 -  Abstract Factory Pattern

- What pattern is it? Abstract Factory Pattern
- What is its goal in the code? The ChocolateEggFactory is an interface 
for creating families of related object, while letting the subclasses HollowEggFactor and StuffedEggFactory
 decide what type of egg to instantiate. The ChocolateEggFactory method
 defer instantiation to subclasses HollowEggFactor and StuffedEggFactory.
- Name of UML Class diagram attached: egg.uml
- Mapping to GoF pattern elements:

| GoF element           | Code element               |
|-----------------------|----------------------------|
| AbstractCreator       | ChocolateEggFactory        |
| Concrete creator      | HollowEggFactory           |
| Concrete creator      | StuffedEggFactory          |
| Concrete product      | StuffedChocolateEgg        |
| Concrete product      | HollowChocolateEggFactory  |
| Abstract product      | ChocolateEgg               |
| Factory Method        | createChocolateEgg()       |
| Client                | PreparingOrder             |






### Pattern 2 -  ??

- What pattern is it? Command Pattern
- What is its goal in the code? To encapsulate a request, in this case an order, as an object.
                                This lets you parameterize clients with different chocolate requests. - 
                                The counter takes an order command from a customer and encapsulates that
                                order. The order is then queued for a chocolatier.
- Name of UML Class diagram attached:
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
| Command               | Order                 |
| Concrete Command      | PrepareOrder          |
| Receiver              | Chocolatier           |
| Invoker               | Counter               |
| Client                | App                   |

### Pattern 3 - ??

- What pattern is it? Composite Pattern
- What is its goal in the code? 
The composite, HollowChocolateEgg can be treated atomically just like a leaf. 
The ChocolateEgg is the base abstract component for all the objects in the composition. The StuffedChocolateEgg
implements the default behaviour of the ChocolateEgg while not needing it to contain reference to the other objects.
The client has access to the composition elements by using the base component object. This allows it to make it easy
to add new components and allows treating individual objects and composites in the same way.
- Name of UML Class diagram attached:
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
| Component             | ChocolateEgg          |
| Composite             | HollowChocolateEgg    |
| Leaf                  | StuffedChocolateEgg   |
| Client                | PreparingOrder        |

## Task 2 - Full UML Class diagram

- Name of file of full UML Class diagram attached:
- More explanation (if needed):

## Task 3 - Implement new features

### Task 3.1 - balanced packaging 

- What pattern fulfils the need for the feature? Strategy Pattern.
- What is its goal and why is it needed here? The strategy pattern can be used to define a family of algorithms and
ecapsulate each one and make them interchangeable. We can do this for our different packaging types, as the algorithims
for each packaging type will independently vary depending on the choices made by the client.

Clients can couple themselves to an interface and not have to experience the upheaval associated with change. There 
should be no impact when the implementation of the packaging type class changes.

It is needed here as we can have high cohesion and minimize tight coupling. By using the strategy pattern we are 
programming to an interface and not an implementation.
- Name of UML Class diagram attached: 
- Mapping to GoF pattern elements:

| GoF element           | Code element                     |
|-----------------------|----------------------------------|
| Context               | PreparingOrder                   |
| Strategy              | PrepareStrategy                  |
| ConcreteStrategyA     | RegularBoxStrategy               |
| ConcreteStrategyB     | MixedBoxStrategy                 |
| ConcreteStrategyC     | RegularHollowEggStrategy         |
| ConcreteStrategyD     | MixedHollowEggStrategy           |
| Algorithm             | prepare()                        |

### Task 3.2 - fill in the packages with eggs

- What pattern fulfils the need for the feature? Iterator Pattern
- What is its goal and why is it needed here? To use an iterator to traverse our list of eggs. It is needed here as we 
want to access the eggs in the packaging and traverse through the eggs of the packaging without exposing its data
structure. It is a way to provide access to the elements (the eggs) of an aggregate object (the package) sequentially
without exposing its underlying representation.
- Name of UML Class diagram attached: 
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
|                       |                       |

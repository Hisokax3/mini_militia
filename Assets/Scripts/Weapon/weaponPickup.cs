using UnityEngine;
using UnityEngine.InputSystem;

public class weaponPickup : MonoBehaviour
{
    public int weapondIndex;
    private bool playerIsNearby;

    private void Update()
    {
        // Check for player input to pick up the weapon
        if (playerIsNearby && Keyboard.current.eKey.isPressed)
        {
            print("attempting to pick up weapon index: " + weapondIndex);
            PickUp();
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        // Check if the collider entering the trigger zone is the player
        if (other.CompareTag("Player"))
        {
            playerIsNearby = true;
            // Optionally, show a UI prompt to the player
            Debug.Log("Press 'E' to pick up " + weapondIndex);
        }
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        // Reset the flag when the player leaves the trigger zone
        if (other.CompareTag("Player"))
        {
            playerIsNearby = false;
        }
    }

    private void PickUp()
    {
        // Find the player's WeaponManager
        WeaponManager playerWeaponManager = FindObjectOfType<WeaponManager>();
        if (playerWeaponManager != null)
        {
            print("player weapon manager: " + weapondIndex);
            playerWeaponManager.AddWeapon(weapondIndex);
        }

        // Destroy the physical pickup object from the scene
        Destroy(gameObject);
    }
}

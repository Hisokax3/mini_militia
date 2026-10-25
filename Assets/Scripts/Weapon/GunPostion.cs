using System;
using UnityEngine;
using UnityEngine.InputSystem;

public class Gun : MonoBehaviour
{
    private Camera gameCamera;
    private Transform playerTransform;
    
    private void Awake()
    {
        gameCamera = Camera.main;
        playerTransform = transform.parent;
    }

    private void FixedUpdate()
    {
        HandleGunRotation();   
    }


    private void HandleGunRotation()
    {
        Vector3 mousePosition = gameCamera.ScreenToWorldPoint(Mouse.current.position.ReadValue());
        mousePosition.z = 0f;
        
        Vector2 direction = (mousePosition - playerTransform.position).normalized;
        
        float angle = Mathf.Atan2(direction.y, direction.x) * Mathf.Rad2Deg;

        bool facingRight = mousePosition.x >= playerTransform.position.x;

        if(facingRight){
            transform.rotation = Quaternion.Euler(0, 0, angle);
        }
        else{
            transform.rotation = Quaternion.Euler(180, 0, -angle);
        }
        
    }

}
